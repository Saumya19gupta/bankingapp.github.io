package com.java.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.entity.Account;
import com.java.entity.Transaction;
import com.java.repository.AccountRepository;
import com.java.repository.TransactionRepository;
import com.java.service.AccountService;
import com.java.utility.TimeDateUtility;

@Service
public class AccountServiceImpl implements AccountService 
{
	@Autowired private AccountRepository accountRepository;
	@Autowired private TransactionRepository transactionRepository;

	public AccountServiceImpl(AccountRepository accountRepository) 
	{
		this.accountRepository = accountRepository;
	}
	public Account getAccountByUserId(String userid) 
	{
		Account account=accountRepository.findByUserid(userid);
		return account;
	}
	public int getCurrentBalance(long accountno) 
	{
		int amount=accountRepository.getAmount(accountno);
		return amount;
	}
	public Transaction updateMoney(int amount,long accountno,String type) 
	{
		accountRepository.updateAmount(amount,accountno);
		Transaction transaction=saveTransaction(accountno,accountno,amount,type);
		return transaction;
	}
	public Transaction transferAmount(int amount, Long san, Long ran) 
	{
		accountRepository.updateAmount(amount,ran);
		accountRepository.updateAmount(-amount,san);
		Transaction transaction=saveTransaction(san,ran,amount,"Transfer");
		return transaction;
	}
	public Account getAccount(long accountno) 
	{
		return accountRepository.findById(accountno).orElse(null);
	}
	private Transaction saveTransaction(long san,long ran,int amount,String type)
	{
		Transaction transaction=new Transaction();
		transaction.setFromac(san);
		if(amount<0)
			transaction.setAmount(-amount);
		else
				transaction.setAmount(amount);
		transaction.setDate(TimeDateUtility.getCurrentDate());
		transaction.setTime(TimeDateUtility.getCurrentTime());
		transaction.setType(type);
		transaction.setToac(ran);
		transactionRepository.save(transaction);
		return transaction;
	}
}