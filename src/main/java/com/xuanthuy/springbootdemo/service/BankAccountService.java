package com.xuanthuy.springbootdemo.service;

import java.util.List;

import com.xuanthuy.springbootdemo.entity.BankAccount;
import com.xuanthuy.springbootdemo.exception.BankTransactionException;

public interface BankAccountService {
	
	List<BankAccount> getListAccount();
	void persist(BankAccount entity);
	void delete(BankAccount entity);
	void addAmount(Long id,double amount) throws BankTransactionException;
	void sendMoney(Long fromAccountId,Long toAccountId,double amount) throws BankTransactionException;
}
