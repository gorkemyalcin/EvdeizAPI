package com.urlayasam.project.requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.urlayasam.project.models.EventType;

public class EventAddRequest {
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	private String name;
	private EventType type;
	private String place;
	private String address;
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Date startDate;
	@JsonFormat(pattern = "HH:mm")
	private Date startTime;
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Date endDate;
	@JsonFormat(pattern = "HH:mm")
	private Date endTime;
	private String description;
	private String festivalName;
	private String companyName;
	private String longitude;
	private String latitude;
	private String image;

	public EventAddRequest() {

	}

	public EventAddRequest(String name, EventType type, String place, String address, Date startDate, Date startTime,
			Date endDate, Date endTime, String description, String festivalName, String companyName, String longitude, String latitude, String image) {
		super();
		this.name = name;
		this.type = type;
		this.place = place;
		this.address = address;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.description = description;
		this.festivalName = festivalName;
		this.companyName = companyName;
		this.longitude = longitude;
		this.latitude = latitude;
		this.image = image;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getFestivalName() {
		return festivalName;
	}

	public void setFestivalName(String festivalName) {
		this.festivalName = festivalName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String toString() {
		return "Name: " + name + ", Type: " + type + ", Place: " + place + ", Address " + address + ", Start date: "
				+ startDate.toString() + " " + startTime.toString() + ", End date: " + endDate.toString()
				+ ", Description: " + description + ", Festival: " + (festivalName == null ? false : festivalName) + "Company that organizes the event: " + companyName;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
