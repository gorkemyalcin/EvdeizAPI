package com.urlayasam.project.requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CompanyUpdateRequest {
	private Integer id;
	private String name;
	private String email;
	private String telephone;
	private String description;
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm")
	private Date creationDate;
	private String logo;
	
	public CompanyUpdateRequest() {

	}

	public CompanyUpdateRequest(Integer id, String name, String email, String telephone, String description,
			Date creationDate, String logo) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.description = description;
		this.creationDate = creationDate;
		this.logo = logo;
	}

	public String toString() {
		return "Id: " + id + ", Name: " + name + ", Email " + email + ", Telephone: " + telephone + ", Description: "
				+ description + ", CreationDate date: " + creationDate.toString() + ", Logo: " + logo;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
