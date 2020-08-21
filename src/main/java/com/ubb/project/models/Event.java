package com.urlayasam.project.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.urlayasam.project.requests.EventAddRequest;
import com.urlayasam.project.requests.EventUpdateRequest;

@Entity
public class Event implements Comparable<Event> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column
	private String name;
	@Column
	private EventType type;
	@Column
	private String place;
	@Column
	private String address;
	@Column
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Date startDate;
	@Column
	@JsonFormat(pattern = "HH:mm")
	private Date startTime;
	@Column
	private String dayOfTheWeek;
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Date endDate;
	@Column
	@JsonFormat(pattern = "HH:mm")
	private Date endTime;
	@Column
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm")
	private Date registrationDate;
	@Column
	private String description;
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "festivalId")
	@NotFound(action = NotFoundAction.IGNORE)
	private Festival festival;
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "companyId")//If a company gets deleted, then re added, because of this it won't reassign itself to the event.
	@NotFound(action = NotFoundAction.IGNORE)
	private Company company;
	@Column
	private String longitude;
	@Column
	private String latitude;
	@Column
	private String image;
	@Column
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm")
	private Date lastModificationDate;
	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	@JsonIgnore
    private List<Comment> comment;

	public Event() {

	}

	public Event(Integer id, String title, EventType type, String place, String address, Date startDate, Date startTime,
			Date endDate, Date endTime, String description, Date registrationDate, Festival festival, Company company,
			String longitude, String latitude, String image, Date lastModificationDate) {
		super();
		this.id = id;
		this.name = title;
		this.type = type;
		this.place = place;
		this.address = address;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.description = description;
		this.registrationDate = registrationDate;
		this.festival = festival;
		this.company = company;
		this.longitude = longitude;
		this.latitude = latitude;
		this.image = image;
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
		this.dayOfTheWeek = simpleDateformat.format(startDate);
		this.lastModificationDate = lastModificationDate;
	}

	public void setEvent(EventUpdateRequest eventUpdateRequest, Festival festival, Company company) {
		this.id = eventUpdateRequest.getId();
		this.name = eventUpdateRequest.getName();
		this.type = eventUpdateRequest.getType();
		this.place = eventUpdateRequest.getPlace();
		this.address = eventUpdateRequest.getAddress();
		this.startDate = eventUpdateRequest.getStartDate();
		this.startTime = eventUpdateRequest.getStartTime();
		this.endDate = eventUpdateRequest.getEndDate();
		this.endTime = eventUpdateRequest.getEndTime();
		this.description = eventUpdateRequest.getDescription();
		this.festival = festival;
		this.company = company;
		this.longitude = eventUpdateRequest.getLongitude();
		this.latitude = eventUpdateRequest.getLatitude();
		this.image = eventUpdateRequest.getImage();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
		this.dayOfTheWeek = simpleDateformat.format(eventUpdateRequest.getStartDate());
		this.lastModificationDate = new Date();
	}

	public void setEvent(EventAddRequest eventAddRequest, Festival festival, Company company) {
		this.name = eventAddRequest.getName();
		this.type = eventAddRequest.getType();
		this.place = eventAddRequest.getPlace();
		this.address = eventAddRequest.getAddress();
		this.startDate = eventAddRequest.getStartDate();
		this.startTime = eventAddRequest.getStartTime();
		this.endDate = eventAddRequest.getEndDate();
		this.endTime = eventAddRequest.getEndTime();
		this.description = eventAddRequest.getDescription();
		this.festival = festival;
		this.registrationDate = new Date();
		this.company = company;
		this.longitude = eventAddRequest.getLongitude();
		this.latitude = eventAddRequest.getLatitude();
		this.image = eventAddRequest.getImage();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
		this.dayOfTheWeek = simpleDateformat.format(eventAddRequest.getStartDate());
		this.lastModificationDate = new Date();
	}

	public String toString() {
		return "Id: " + id + ", Name: " + name + ", Type: " + type + ", Place: " + place + ", Address " + address
				+ ", Start date: " + startDate.toString() + " " + startTime.toString() + ", End date: "
				+ endDate.toString() + " " + endTime.toString() + ", Description: " + description
				+ ", Registration date: " + registrationDate.toString() + ", Festival: "
				+ (festival == null ? false : festival) + ", Organizator company: "
				+ (company == null ? false : company) + ", Longitude: " + longitude + ", Latitude: " + latitude
				+ ", Image" + image + ", Day of the week: " + dayOfTheWeek;
	}

	public int compareTo(Event otherEvent) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		SimpleDateFormat dateFormatForDay = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat dateFormatForTime = new SimpleDateFormat("HH:mm");
		try {
			Date thisDate = dateFormatter.parse(dateFormatForDay.format(this.getStartDate()).toString() + " "
					+ dateFormatForTime.format(this.getStartDate()).toString());
			Date otherDate = dateFormatter.parse(dateFormatForDay.format(otherEvent.getStartDate()).toString() + " "
					+ dateFormatForTime.format(otherEvent.getStartDate()).toString());
			return thisDate.compareTo(otherDate);
		} catch (ParseException e) {
		}
		return 0;
	}

	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	public String getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	public void setDayOfTheWeek(String dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setFestival(Festival festival) {
		this.festival = festival;
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Festival getFestival() {
		return festival;
	}

	public void setFestivalId(Festival festival) {
		this.festival = festival;
	}
}
