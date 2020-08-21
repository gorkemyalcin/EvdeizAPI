package com.urlayasam.project.requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.urlayasam.project.models.EventType;

public class EventUpdateRequest {
	private Integer id;
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
	private Integer festivalId;
	private Integer companyId;
	private String longitude;
	private String latitude;
	private String image;

	public EventUpdateRequest() {

	}

	public EventUpdateRequest(Integer id, String name, EventType type, String place, String address, Date startDate,
			Date startTime, Date endDate, Date endTime, String description, Integer festivalId, Integer companyId, String longitude, String latitude, String image) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.place = place;
		this.address = address;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.description = description;
		this.festivalId = festivalId;
		this.companyId = companyId;
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

	public String toString() {
		return "Id: " + id + ", Name: " + name + ", Type: " + type + ", Place: " + place + ", Address " + address
				+ ", Start date: " + startDate.toString() + " " + startTime.toString() + ", End date: "
				+ endDate.toString() + " " + endTime.toString() + ", Description: " + description
			 + ", Festival id: "
				+ (festivalId == null ? false : festivalId) + ", Company that organizes the event: " + companyId + ", Image: " + image;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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

	public Integer getFestivalId() {
		return festivalId;
	}

	public void setFestivalId(Integer festivalId) {
		this.festivalId = festivalId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
