package com.xuanthuy.springbootdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xuanthuy.springbootdemo.dao.interfaces.RoleDAO;
import com.xuanthuy.springbootdemo.dao.interfaces.UserinfoDAO;
import com.xuanthuy.springbootdemo.entity.Userinfo;

@Service
public class UserDetailsServiceImp implements UserDetailsService{

	@Autowired
	@Lazy
	private UserinfoDAO userinfoDAO;
	
	@Autowired
	private RoleDAO roledao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Userinfo user = userinfoDAO.findUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Khong ton tai tai khoan "+username);
		}
		//Tim ROLE cua user
		List<String> roleName = this.roledao.findById(user.getId());
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if(roleName != null) {
			for(String role:roleName) {
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}
		
		UserDetails userDetails = new User(user.getUsername(),user.getEncryptedPass(),grantList);
		return userDetails;
	}

}
