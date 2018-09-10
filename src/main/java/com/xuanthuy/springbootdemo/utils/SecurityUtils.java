package com.xuanthuy.springbootdemo.utils;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.security.SocialUserDetails;

import com.xuanthuy.springbootdemo.entity.Userinfo;
import com.xuanthuy.springbootdemo.social.SocialUserDetailsImpl;

//Tự động đăng nhập tài khoản mạng xã hội
public class SecurityUtils {
	
	public static void loginUser(Userinfo userinfo,List<String> listRoleName) {
		SocialUserDetails userDetails = new SocialUserDetailsImpl(listRoleName, userinfo);
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
