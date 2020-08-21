package com.urlayasam.project.requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FestivalAddRequest {
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

	public FestivalAddRequest() {

	}

	public FestivalAddRequest(String name, String address, Date startDate, Date startTime, Date endDate, Date endTime,
			String description, Date creationDate) {
		super();
		this.name = name;
		this.address = address;
		this.startDate = startDate;
		this.startTime = startTime;
		this.description = description;
		this.endDate = endDate;
		this.endTime = endTime;
	}

	public String toString() {
		return ", Name: " + name + ", Address " + address + ", Start date: " + startDate.toString() + " "
				+ startTime.toString() + ", End date: " + endDate.toString() + " " + endTime.toString() + ", Description: " + description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
