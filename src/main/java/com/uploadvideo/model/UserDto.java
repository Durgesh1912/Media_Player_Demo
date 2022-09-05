package com.uploadvideo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

@Entity
@Table(name="USER_DETAILS")
public class UserDto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="FIRST_NAME")
	@NotBlank(message = "Please provide valid First Name")
	private String firstName;
	
	@Column(name="LAST_NAME")
	@NotBlank(message = "Please provide valid Last Name")
	private String lastName;
	
	@Column(name="EMAIL")
	@NotBlank(message = "Please provide valid Email Address")
	private String emailAddress;
	
	@Column(name="PASSWORD")
	@NotBlank(message = "Please provide  valid PassWord")
	private String password;
	
	
	@Column(name ="CREATED_ON")
	private Date createdOn;
	
	@Column(name ="UPDATED_ON")
	private Date updatedOn;
	
	@PrePersist
	protected void onCreate() {
		this.createdOn=new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedOn=new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
