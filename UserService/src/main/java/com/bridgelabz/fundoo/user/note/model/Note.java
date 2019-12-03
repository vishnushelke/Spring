/******************************************************************************
*  Purpose: This class is an entity of user
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   01-11-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.note.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.bridgelabz.fundoo.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "note")
@Getter
@Setter
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "node_id")
	private int noteId;
	@Column(name = "user_id")
	@NotNull(message = "UserId of note can not be null")
	private int userId;
	private String title;
	private String text;
	private boolean trash;
	private boolean pin;
	private boolean archive;
//	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date reminder;
	private String colour;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date noteCreationDate;
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date noteUpdationDate;
	@JsonIgnoreProperties(value = "notes")
	@ManyToMany(cascade = CascadeType.MERGE)
	private List<Label> labels;
	@JsonIgnoreProperties(value = "notes")
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private List<User> collabUsers;
//	@Override
//	public String toString() {
//		return "Note [noteId=" + noteId + ", userId=" + userId + ", title=" + title + ", text=" + text + ", trash="
//				+ trash + ", pin=" + pin + ", archive=" + archive + ", reminder=" + reminder + ", colour=" + colour
//				+ ", noteCreationDate=" + noteCreationDate + ", noteUpdationDate=" + noteUpdationDate + ", labels="
//				+ labels +  "]";
//	}
	
}
