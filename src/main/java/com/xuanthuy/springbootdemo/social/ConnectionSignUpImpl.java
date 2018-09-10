package com.xuanthuy.springbootdemo.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

import com.xuanthuy.springbootdemo.dao.interfaces.UserinfoDAO;
import com.xuanthuy.springbootdemo.entity.Userinfo;

public class ConnectionSignUpImpl implements ConnectionSignUp {

	private UserinfoDAO userinfo;
	
	public ConnectionSignUpImpl(UserinfoDAO userinfo) {
		// TODO Auto-generated constructor stub
		this.userinfo = userinfo;
	}
	//Sau khi login vao social
	//Phuong thuc nay duoc goi de tao ra mot ban ghi User moi neu no khong ton tai
	@Override
	public String execute(Connection<?> connection) {
		// TODO Auto-generated method stub
		Userinfo user = userinfo.createUserAuto(connection);
		return user.getUsername();
	}

}
