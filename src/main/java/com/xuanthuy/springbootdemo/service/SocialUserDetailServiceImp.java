package com.xuanthuy.springbootdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import com.xuanthuy.springbootdemo.social.SocialUserDetailsImpl;

public class SocialUserDetailServiceImp implements SocialUserDetailsService {

	@Autowired
	private UserDetailsService userDetailService;
	
	//Sau khi user duoc tao boi ConnectionSignUpImp.excute
	// method nay se duoc goi boi Spring Social Api
	@Override
	public SocialUserDetails loadUserByUserId(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDetails userDetails = ((UserDetailsServiceImp) userDetailService).loadUserByUsername(userName);
		return (SocialUserDetailsImpl) userDetails;
	}

}
