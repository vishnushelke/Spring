package com.bridgelabz.fundoo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.fundoo.user.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
}
