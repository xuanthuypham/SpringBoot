package com.xuanthuy.springbootdemo.social;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

import com.xuanthuy.springbootdemo.entity.Userinfo;

public class SocialUserDetailsImpl implements SocialUserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 827649920697395180L;
	
	private List<GrantedAuthority> list = new ArrayList<>();
	
	private Userinfo userinfo;
	
	
	public SocialUserDetailsImpl(List<String> listRoleName, Userinfo userinfo) {
		this.userinfo = userinfo;
		for(String roleName:listRoleName) {
			GrantedAuthority grant = new SimpleGrantedAuthority(roleName);
			this.list.add(grant);
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.userinfo.getEncryptedPass();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userinfo.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.userinfo.getId().toString();
	}

}
