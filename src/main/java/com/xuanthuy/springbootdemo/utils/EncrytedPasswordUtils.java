package com.xuanthuy.springbootdemo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncrytedPasswordUtils {

	//Endcode password with BCryptPasswordEndcoder
	public static String encrytePassword(String password) {
		BCryptPasswordEncoder endcoder = new BCryptPasswordEncoder();
		return endcoder.encode(password);
	}
	
	public static void main(String[] args) {
		String password = "xuanthuy";
		String encrytedPassword = encrytePassword(password);
		System.out.println(encrytedPassword);
	}
	
}
