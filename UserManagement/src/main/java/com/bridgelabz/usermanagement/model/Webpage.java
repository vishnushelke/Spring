package com.bridgelabz.usermanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Webpage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int webpageId;
	private int userId;
	private boolean addPermission;
	private boolean deletePermission;
	private boolean modifyPermission;
	private boolean readPermission;
}
