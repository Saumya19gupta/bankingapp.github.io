package com.java.service;

import com.java.entity.Account;
import com.java.entity.Transaction;

public interface AccountService 
{
	Account getAccountByUserId(String userid);
	int getCurrentBalance(long accountno);
	Transaction updateMoney(int amount,long accountno,String type);
	Account getAccount(long accountno);
	Transaction transferAmount(int amount, Long an, Long attribute);
}