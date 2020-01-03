package com.bridgelabz.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bridgelabz.usermanagement.model.LoginHistory;
@Repository
public interface LoginHistoryRepo extends JpaRepository<LoginHistory, Integer>{
	
}
