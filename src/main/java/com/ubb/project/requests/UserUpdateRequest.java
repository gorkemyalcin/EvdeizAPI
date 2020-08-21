package com.urlayasam.project.requests;

import com.urlayasam.project.models.UserType;

public class UserUpdateRequest {
	private Integer id;
	private String username;
	private String email;
	private String password;
	private UserType userType;

	public String toString() {
		return "Username: " + username + ", Email: " + email + ", User type: " + userType.toString();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public void setPassword1(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
