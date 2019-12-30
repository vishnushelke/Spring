package com.bridgelabz.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.usermanagement.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
