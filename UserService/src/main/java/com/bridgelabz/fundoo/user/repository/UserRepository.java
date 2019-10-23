/******************************************************************************
*  Purpose: This is Repository which extends JpaRepository
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   23-10-2019
*
******************************************************************************/

package com.bridgelabz.fundoo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.fundoo.user.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
}
