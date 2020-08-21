package com.urlayasam.project.requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FestivalUpdateRequest {

	private Integer id;
	private String name;
	private String address;
	private String description;
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Date startDate;
	@JsonFormat(pattern = "HH:mm")
	private Date startTime;
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Date endDate;
	@JsonFormat(pattern = "HH:mm")
	private Date endTime;
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm")
	private Date creationDate;

	public FestivalUpdateRequest(Integer id, String name, String address, String description, Date startDate,
			Date startTime, Date endDate, Date endTime, Date creationDate) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.creationDate = creationDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String toString() {
		return "Id: " + id + ", Name: " + name + ", Address " + address + ", Start date: " + startDate.toString() + " "
				+ startTime.toString() + ", End date: " + endDate.toString() + " " + endTime.toString()
				+ ", Description: " + description + ", CreationDate date: " + creationDate.toString();
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
