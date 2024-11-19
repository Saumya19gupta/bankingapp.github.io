package com.java.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.entity.Account;
import com.java.entity.User;
import com.java.repository.AccountRepository;
import com.java.repository.UserRepository;
import com.java.service.UserService;

@Service
public class UserServiceImpl implements UserService 
{
	@Autowired	private UserRepository userRepository;
	@Autowired	private AccountRepository accountRepository;

	public long saveUser(User user) 
	{
		userRepository.save(user);
		Account account=new Account();
		account.setUserid(user.getUserid());
		accountRepository.save(account);
		return account.getAccountno();
	}
	public User getUser(String userid) 
	{
		User user=userRepository.findById(userid).orElse(null);
		return user;
	}
}
