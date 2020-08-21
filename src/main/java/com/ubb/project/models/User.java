package com.urlayasam.project.models;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.urlayasam.project.requests.UserAddRequest;
import com.urlayasam.project.requests.UserUpdateRequest;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private UserType userType;
	@Column
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm")
	private Date creationDate;
	@Column
	private Date lastModificationDate;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
    private List<Comment> comment;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
    private List<Favorite> favorite;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
    private List<Feedback> feedback;

	public User() {

	}

	public User(Integer id, String username, String password, String email, UserType userType, Date creationDate,
			Date lastModificationDate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.userType = userType;
		this.creationDate = creationDate;
		this.lastModificationDate = lastModificationDate;
	}

	public String toString() {
		return "Id: " + id + ", Username: " + username + ", Email " + email + ", Password: " + password
				+ ", User type: " + userType + ", Creation Date: " + creationDate.toString();
	}

	public void setUEUser(@Valid UserUpdateRequest updateUserRequest) {
		this.setEmail(updateUserRequest.getEmail());
		this.setId(updateUserRequest.getId());
		this.setPassword(updateUserRequest.getPassword());
		this.setUsername(updateUserRequest.getUsername());
		this.setUserType(updateUserRequest.getUserType());
		this.lastModificationDate = new Date();
	}

	public void setUser(@Valid UserAddRequest paramUser) throws ParseException {
		this.setEmail(paramUser.getEmail());
		this.setPassword(paramUser.getPassword());
		this.setUsername(paramUser.getUsername());
		this.setUserType(paramUser.getUserType());
		this.setCreationDate(new Date());
		this.lastModificationDate = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
