package com.bridgelabz.usermanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "u_id")
	private int uId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String emailId;
	private String dateOfBirth;
	private String gender;
	private String country;
	private long phoneNumber;
	private long extraPhone;
	private String address;
	private String userName;
	private String password;
	private String userRole;
	private boolean status;
	private String profilePic;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date creationalDate;
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updationalDate;
	@Override
	public String toString() {
		return "User [uId=" + uId + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", emailId=" + emailId + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", country="
				+ country + ", phoneNumber=" + phoneNumber + ", extraPhone=" + extraPhone + ", address=" + address
				+ ", userName=" + userName + ", password=" + password + ", userRole=" + userRole + ", status=" + status
				+ ", profilePic=" + profilePic + ", creationalDate=" + creationalDate + ", updationalDate="
				+ updationalDate + "]";
	}
	
}
