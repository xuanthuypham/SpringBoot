package com.xuanthuy.springbootdemo.dao.interfaces;

import java.util.List;

import org.springframework.social.connect.Connection;

import com.xuanthuy.springbootdemo.entity.*;
import com.xuanthuy.springbootdemo.form.UserInfoForm;
public interface UserinfoDAO{
	
	List<Userinfo> getList();
	Userinfo findUsername(String username);
	void createUser(UserInfoForm form);
	Userinfo findByEmail(String email);
	Userinfo createUserAuto(Connection<?> connection);
}
