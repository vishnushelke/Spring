package com.bridgelabz.fundoo.user.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "user")
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
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date creationDate;
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updationDate;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return uId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public Date getUpdationDate() {
		return updationDate;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	@Override
	public String toString() {
		return "User [uId=" + uId + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", isactive=" + isactive + ", password=" + password + ", creationDate=" + creationDate
				+ ", updationDate=" + updationDate + "]";
	}

}
