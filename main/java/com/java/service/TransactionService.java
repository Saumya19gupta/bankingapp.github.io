package com.java.service;

import java.util.List;

import com.java.entity.Transaction;

public interface TransactionService 
{
	List<Transaction> getTransactionList(Long attribute);
}