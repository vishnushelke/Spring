/******************************************************************************
*  Purpose: This class is an entity of Labels
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   1-11-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.note.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "label")
public class Label {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer labelId;
	@NotNull(message = "name of the label can not be kept null")
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date creationTime;
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updationTime;
	@JsonIgnoreProperties(value = "labels")
	@ManyToMany(mappedBy = "labels")
	private List<Note> notes;

	public Integer getLabelId() {
		return labelId;
	}

	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getUpdationTime() {
		return updationTime;
	}

	public void setUpdationTime(Date updationTime) {
		this.updationTime = updationTime;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

}
