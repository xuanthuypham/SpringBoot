package com.xuanthuy.springbootdemo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xuanthuy.springbootdemo.dao.interfaces.BankAccountDAO;
import com.xuanthuy.springbootdemo.dao.interfaces.UserinfoDAO;
import com.xuanthuy.springbootdemo.entity.BankAccount;
import com.xuanthuy.springbootdemo.entity.Userinfo;
import com.xuanthuy.springbootdemo.exception.BankTransactionException;
import com.xuanthuy.springbootdemo.form.SendMoneyForm;
import com.xuanthuy.springbootdemo.form.UserInfoForm;
import com.xuanthuy.springbootdemo.service.BankAccountService;
import com.xuanthuy.springbootdemo.validator.UserinforValidator;

@Controller
public class MainController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private BankAccountService bankAccountService;
	
	@Autowired
	private UserinfoDAO dao;
	
	@Autowired
	private UserinforValidator userinfovalidator;
	
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String showBankAccount(Model model) {
        LOGGER.trace("This is TRACE");
        LOGGER.debug("This is DEBUG");
        LOGGER.info("This is INFO");
        LOGGER.warn("This is WARN");
        LOGGER.error("This is ERROR");
        
		List<BankAccount> list = bankAccountService.getListAccount();
		model.addAttribute("accountlist", list);
		return "accountPage";
	}
	
	@RequestMapping(value = "/sendMoney",method = RequestMethod.GET)
	public String viewSendMoneyPage(Model model) {
		SendMoneyForm form = new SendMoneyForm(1L,2L,700d);
		model.addAttribute("sendMoneyForm", form);
		return "sendMoneyPage";
	}
	
	//Hien thi form dang ky
	@RequestMapping(value = "/register", method=RequestMethod.GET)
	public String Register(Model model) {
		UserInfoForm form = new UserInfoForm(); //Nap vao form dang ky
		model.addAttribute("userinfoform",form);
		return "register";
	}
	
	//Set a form with a validator
	@InitBinder
	protected void InitBinder(WebDataBinder dataBinder) {
		//Form muc tieu
		Object target = dataBinder.getTarget();
		if(target == null) {
			return;
		}
		if(target.getClass() == UserInfoForm.class) {
			dataBinder.setValidator(userinfovalidator);
		}
	}
	//@Validate de dam bao form da duoc validate khi duoc goi
	//Form nay dung de luu thong tin dang ky
	@PostMapping(value = "/register")
	public String saveRegister(Model model, @ModelAttribute("userinfoform") @Validated UserInfoForm form,
			BindingResult result, final RedirectAttributes redirectAtribute) {
		if(result.hasErrors()) {
			return "register";
		}
		try {
			this.dao.createUser(form);
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "register";
		}
		
		return "redirect:/registersuccessful";
		
	}
	
	@RequestMapping(value = "/registersuccessful")
	public String successfulRegister() {
		return "registersuccess";
	}
	
	@RequestMapping(value = "/sendMoney", method = RequestMethod.POST)
	public String processSendMoney(Model model,SendMoneyForm sendMoneyForm) {
		
		try {
			bankAccountService.sendMoney(sendMoneyForm.getFromAccountId(), sendMoneyForm.getToAccountId(), sendMoneyForm.getAmount());
		} catch (BankTransactionException e) {
			// TODO Auto-generated catch block
			model.addAttribute("errorMessage", e.getMessage());
			return "sendMoneyPage";
		}
		return "redirect:/";
	}
	
	
	@ResponseBody
	@RequestMapping(path = "/logging")
	public String home() {
        LOGGER.trace("This is TRACE");
        LOGGER.debug("This is DEBUG");
        LOGGER.info("This is INFO");
        LOGGER.warn("This is WARN");
        LOGGER.error("This is ERROR");
        return "Show loggin in console or file";
	}
	
	@RequestMapping(value = "/login")
	public String staticResource(Model model) {
		return "login";
	}
	@RequestMapping(value = "/index")
	public String helloBotstrap(Model model) {
		return "index-demo";
	}
	@GetMapping(value="/signin")
	public String sigInPage(Model model) {
		return "redirect:success";
	}
}
