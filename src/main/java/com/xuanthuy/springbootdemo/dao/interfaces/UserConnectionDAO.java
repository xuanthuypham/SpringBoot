package com.xuanthuy.springbootdemo.dao.interfaces;

import com.xuanthuy.springbootdemo.entity.UserConnection;

public interface UserConnectionDAO {

	UserConnection findUserConnectionByUserProviderId(String userProviderId);
}
