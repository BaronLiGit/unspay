package com.baron.dao;

import com.baron.entity.User;

public interface UserDao {
	public User selectUserById(Integer userId);  
}
