/******************************************************************************
*  Purpose: This is Repository of label which extends JpaRepository
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   02-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.note.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.user.note.model.Label;
import java.util.Optional;


@Repository
public interface ILabelRepository extends JpaRepository<Label, Integer>{

	Optional<Label> findByName(String name);
}
