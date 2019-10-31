package com.bridgelabz.fundoo.note.model;

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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
