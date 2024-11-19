package com.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> 
{
	@Query("from Transaction where fromac=:arg or toac=:arg")
	List<Transaction> findByAccountNo(@Param("arg") Long accountno);
}
