package com.bridgelabz.fundoo.note.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.note.model.Label;


@Repository
public interface ILabelRepository extends JpaRepository<Label, Integer>{

}
