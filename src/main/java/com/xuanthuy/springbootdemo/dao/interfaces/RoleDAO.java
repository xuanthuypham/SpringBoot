package com.xuanthuy.springbootdemo.dao.interfaces;

import java.util.List;

import com.xuanthuy.springbootdemo.entity.*;
public interface RoleDAO {
	List<Role> getList();
	List<String> findById(long id);
	Role findRoleByName(String roleName);
	void insert(Userinfo user, List<String> roleList);

}
