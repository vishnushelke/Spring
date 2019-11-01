package com.bridgelabz.fundoo.note.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LabelDto {

	@NotNull(message = "name cannot be null")
	private String name;
	private Date creationTime;
	private Date updationTime;
}
