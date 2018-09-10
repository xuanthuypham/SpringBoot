package com.xuanthuy.springbootdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xuanthuy.springbootdemo.dao.interfaces.BankAccountDAO;
import com.xuanthuy.springbootdemo.entity.BankAccount;
import com.xuanthuy.springbootdemo.exception.BankTransactionException;

@Service
public class BankAccountServiceImp implements BankAccountService {

	@Autowired
	private BankAccountDAO bankAccountDAO;
	
	@Override
	public List<BankAccount> getListAccount() {
		// TODO Auto-generated method stub
		return bankAccountDAO.getList();
	}

	@Override
	public void persist(BankAccount entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BankAccount entity) {
		// TODO Auto-generated method stub
		
	}
	//MANDATORY: Giao dich bat buoc phai duoc tao san truoc do
	
	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void addAmount(Long id, double amount) throws BankTransactionException {
		// TODO Auto-generated method stub
		BankAccount account = bankAccountDAO.findById(id);
		
		if(account == null) {
			throw new BankTransactionException("Tai khoan khong ton tai");
		}
		double newBalance = account.getBalance() + amount;
		if(account.getBalance() + amount <0) {
			throw new BankTransactionException("Tien trong tai khoan khong du .So du su dung "+account.getBalance());
		}
		account.setBalance(newBalance);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
	public void sendMoney(Long fromAccountId, Long toAccountId, double amount) throws BankTransactionException {
		// TODO Auto-generated method stub
		this.addAmount(toAccountId, amount);
		this.addAmount(fromAccountId, -amount);
	}
}
