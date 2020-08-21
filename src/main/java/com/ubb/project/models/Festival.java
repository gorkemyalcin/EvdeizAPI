package com.urlayasam.project.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.urlayasam.project.requests.FestivalAddRequest;
import com.urlayasam.project.requests.FestivalUpdateRequest;

@Entity
public class Festival {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column
	private String name;
	@Column
	private String address;
	@Column
	private String description;
	@Column
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Date startDate;
	@Column
	@JsonFormat(pattern = "HH:mm")
	private Date startTime;
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Date endDate;
	@Column
	@JsonFormat(pattern = "HH:mm")
	private Date endTime;
	@Column
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm")
	private Date creationDate;
	@Column
	private String dayOfTheWeek;
	@Column
	private Date lastModificationDate;
	@OneToMany(mappedBy = "festival", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JsonIgnore
    private List<Event> event;

	public Festival() {

	}

	public Festival(Integer id, String name, String address, Date startDate, Date startTime, Date endTime, Date endDate,
			String description, Date creationDate, String dayOfTheWeek, Date lastModificationDate) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.startDate = startDate;
		this.startTime = startTime;
		this.description = description;
		this.endDate = endDate;
		this.startTime = startTime;
		this.creationDate = creationDate;
		this.dayOfTheWeek = dayOfTheWeek;
		this.lastModificationDate = lastModificationDate;
	}

	public void setFestival(FestivalUpdateRequest festivalUpdateRequest) {
		this.id = festivalUpdateRequest.getId();
		this.name = festivalUpdateRequest.getName();
		this.address = festivalUpdateRequest.getAddress();
		this.startDate = festivalUpdateRequest.getStartDate();
		this.startTime = festivalUpdateRequest.getStartTime();
		this.description = festivalUpdateRequest.getDescription();
		this.endDate = festivalUpdateRequest.getEndDate();
		this.endTime = festivalUpdateRequest.getEndTime();
		this.creationDate = festivalUpdateRequest.getCreationDate();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
		this.dayOfTheWeek = simpleDateformat.format(startDate);
		this.lastModificationDate = new Date();
	}

	public void setFestival(FestivalAddRequest festivalAddRequest) {
		this.name = festivalAddRequest.getName();
		this.address = festivalAddRequest.getAddress();
		this.startDate = festivalAddRequest.getStartDate();
		this.startTime = festivalAddRequest.getStartTime();
		this.description = festivalAddRequest.getDescription();
		this.endDate = festivalAddRequest.getEndDate();
		this.endTime = festivalAddRequest.getEndTime();
		this.creationDate = new Date();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
		System.out.println(simpleDateformat.format(startDate));
		this.dayOfTheWeek = simpleDateformat.format(startDate);
		this.lastModificationDate = new Date();
	}

	public String toString() {
		return "Id: " + id + ", Name: " + name + ", Address " + address + ", Start date: " + startDate.toString() + " "
				+ startTime.toString() + ", End date: " + endDate.toString() + " " + endTime.toString()
				+ ", Description: " + description + ", CreationDate date: " + creationDate.toString() + ", Day of the week: " + dayOfTheWeek;
	}

	public Date getStartTime() {
		return startTime;
	}

	public String getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	public void setDayOfTheWeek(String dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}

	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
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
