package com.urlayasam.project.models;

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
import com.urlayasam.project.requests.CompanyAddRequest;
import com.urlayasam.project.requests.CompanyUpdateRequest;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String telephone;
	@Column
	private String logo;
	@Column
	private String description;
	@Column
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm")
	private Date creationDate;
	@Column
	private Date lastModificationDate;
	@OneToMany(mappedBy = "company", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JsonIgnore
    private List<Event> event;

	public Company() {

	}

	public Company(Integer id, String name, String email, String telephone, String description, Date creationDate,
			String logo, Date lastModificationDate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.description = description;
		this.creationDate = creationDate;
		this.lastModificationDate = lastModificationDate;
	}

	public void setCompany(CompanyUpdateRequest companyUpdateRequest) {
		this.id = companyUpdateRequest.getId();
		this.name = companyUpdateRequest.getName();
		this.email = companyUpdateRequest.getEmail();
		this.telephone = companyUpdateRequest.getTelephone();
		this.description = companyUpdateRequest.getDescription();
		this.creationDate = companyUpdateRequest.getCreationDate();
		this.logo = companyUpdateRequest.getLogo();
		this.lastModificationDate = new Date();
	}

	public void setCompany(@Valid CompanyAddRequest paramCompany) {
		this.name = paramCompany.getName();
		this.email = paramCompany.getEmail();
		this.telephone = paramCompany.getTelephone();
		this.description = paramCompany.getDescription();
		this.creationDate = new Date();
		this.logo = paramCompany.getLogo();
		this.lastModificationDate = new Date();
	}

	public String toString() {
		return "Id: " + id + ", Name: " + name + ", Email " + email + ", Telephone: " + telephone + ", Description: "
				+ description + ", CreationDate date: " + creationDate.toString() + ", Logo: " + logo;
	}

	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
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
