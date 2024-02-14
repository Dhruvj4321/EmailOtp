package com.email.verification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.email.verification.entity.User;



public interface UserRepo extends JpaRepository<User, String> {
	
	
	User findByEmail(String email);

}
