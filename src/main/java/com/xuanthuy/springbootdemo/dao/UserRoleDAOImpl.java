package com.xuanthuy.springbootdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xuanthuy.springbootdemo.dao.interfaces.UserRoleDAO;
import com.xuanthuy.springbootdemo.entity.Role;
import com.xuanthuy.springbootdemo.entity.UserRole;
import com.xuanthuy.springbootdemo.entity.Userinfo;

@Repository
@Transactional
public class UserRoleDAOImpl implements UserRoleDAO {

	@Autowired
	private SessionFactory sessionFactory;
	


}
