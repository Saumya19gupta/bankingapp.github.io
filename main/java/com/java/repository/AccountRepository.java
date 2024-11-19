package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.java.entity.Account;

public interface AccountRepository extends JpaRepository<Account,Long> 
{
	Account findByUserid(String userid);

	@Query("select amount from Account where accountno=:arg")
	int getAmount(@Param("arg") long accountno);

	@Modifying
	@Transactional
	@Query("update Account set amount=amount+:arg1 where accountno=:arg2")
	void updateAmount(@Param("arg1") int amount,@Param("arg2") long accountno);
}
