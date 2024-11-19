package com.java.service;

import com.java.entity.User;

public interface UserService 
{
	long saveUser(User user);
	User getUser(String userid);
}