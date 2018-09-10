package com.xuanthuy.springbootdemo.dao.interfaces;

import java.util.List;
import com.xuanthuy.springbootdemo.entity.BankAccount;
import com.xuanthuy.springbootdemo.exception.BankTransactionException;

public interface BankAccountDAO {

	List<BankAccount> getList();
	BankAccount findById(Long id);
	void persist(BankAccount bankAccount);
	void update(BankAccount bankAccount);
	void delete(BankAccount bankAccount);
}
