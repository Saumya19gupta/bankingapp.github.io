package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.entity.User;

public interface UserRepository extends JpaRepository<User,String> 
{
	@Query("select name from User where userid=:arg")
	String findName(@Param("arg") String userid);
}
