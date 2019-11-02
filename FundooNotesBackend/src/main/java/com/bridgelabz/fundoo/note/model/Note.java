package com.bridgelabz.fundoo.note.model;

import java.time.LocalDateTime;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@Entity
@Table(name = "note")
@Data
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
	private LocalDateTime reminder;
	private MultipartFile image;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date noteCreationDate;
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date noteUpdationDate;
	@JsonIgnoreProperties(value = "notes")
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private List<Label> labels;

}
