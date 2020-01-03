package com.bridgelabz.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.usermanagement.model.Webpage;

@Repository
public interface WebpageRepository extends JpaRepository<Webpage, Integer>{

}
