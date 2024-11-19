package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.entity.Transaction;
import com.java.repository.TransactionRepository;
import com.java.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService 
{
	@Autowired private TransactionRepository transactionRepository;

	public List<Transaction> getTransactionList(Long accountno) 
	{
		List<Transaction> tlist=transactionRepository.findByAccountNo(accountno);
		return tlist;
	}
}

