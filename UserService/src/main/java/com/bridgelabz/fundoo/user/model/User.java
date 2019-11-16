/******************************************************************************
*  Purpose: This class is an entity of user
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   23-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.bridgelabz.fundoo.user.note.model.Note;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "u_id")
	private int uId;
	private String firstname;
	private String lastname;
	private String email;
	private boolean isactive;
	private String password;
	private long phonenumber;
	private String profilePicture;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date creationDate;
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updationDate;
	@ManyToMany(mappedBy = "collabUsers")
	private List<Note> notes;

}
