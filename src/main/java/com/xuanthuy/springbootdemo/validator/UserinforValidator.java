package com.xuanthuy.springbootdemo.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.xuanthuy.springbootdemo.dao.interfaces.UserinfoDAO;
import com.xuanthuy.springbootdemo.entity.Userinfo;
import com.xuanthuy.springbootdemo.form.UserInfoForm;

@Component
public class UserinforValidator implements Validator {

	//Use apache common email validator
	private EmailValidator emailValidator = EmailValidator.getInstance();
	
	@Autowired
	private UserinfoDAO dao;
	
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return arg0 == UserInfoForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserInfoForm form = (UserInfoForm) target;
		
		//Validate all feild of above form;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.appUserForm.userName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.appUserForm.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.appUserForm.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.appUserForm.gender");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.appUserForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.appUserForm.confirmPassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.appUserForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryCode", "NotEmpty.appUserForm.countryCode");
		
		
		
		if(!errors.hasFieldErrors("userName")) {
			Userinfo user = dao.findUsername(form.getUserName());
			if(user != null) {
				errors.rejectValue("userName", "Duplicate.appUserForm.userName");
			}
		}
		
		if(!errors.hasFieldErrors("email")) {
			if(!this.emailValidator.isValid(form.getEmail())) {
				errors.rejectValue("email", "Pattern.appUserForm.email");
			} else {
				Userinfo user = dao.findByEmail(form.getEmail());
				if(user != null) {
					errors.rejectValue("email", "Duplicate.appUserForm.email");
				}
			}
		}
		
		
		if(!errors.hasErrors()) {
			if(!form.getConfirmPassword().equals(form.getPassword())) {
				errors.rejectValue("confirmPassword", "Match.appUserForm.confirmPassword");
			}
		}
	}

}
