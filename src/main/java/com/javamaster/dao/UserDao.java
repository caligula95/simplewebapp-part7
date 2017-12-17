package com.javamaster.dao;

import com.javamaster.entity.Users;

public interface UserDao {
	
	int createUser(Users user);
	
	int editUser(Users user);
	
	Users getUserByPasswordAndLogin(String password, String login);

}
