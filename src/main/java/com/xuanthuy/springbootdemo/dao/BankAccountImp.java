package com.xuanthuy.springbootdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xuanthuy.springbootdemo.dao.interfaces.BankAccountDAO;
import com.xuanthuy.springbootdemo.entity.BankAccount;
import com.xuanthuy.springbootdemo.exception.BankTransactionException;

@Repository
@Transactional
public class BankAccountImp implements BankAccountDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BankAccount> getList() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Query<BankAccount> que = session.createQuery("from BankAccount");
		return que.getResultList();
	}

	@Override
	public BankAccount findById(Long id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(BankAccount.class, id); // session.load(BankAccount.class,id)
	}

	@Override
	public void persist(BankAccount bankAccount) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(bankAccount);
	}

	@Override
	public void update(BankAccount bankAccount) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(bankAccount);
	}

	@Override
	public void delete(BankAccount bankAccount) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(bankAccount);
	}
	
}
