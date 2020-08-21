package com.urlayasam.project.requests;

public class CompanyAddRequest {
	private String name;
	private String email;
	private String telephone;
	private String description;
	private String logo;

	public CompanyAddRequest() {

	}

	public CompanyAddRequest(String name, String email, String telephone, String description, String logo) {
		super();
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.description = description;
		this.logo = logo;
	}

	public String toString() {
		return "Name: " + name + ", Email " + email + ", Telephone: " + telephone + ", Description: "
				+ description + ", Logo: " + logo ;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
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

}
