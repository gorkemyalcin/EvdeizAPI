package com.urlayasam.project.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.urlayasam.project.requests.FavoriteAddRequest;
import com.urlayasam.project.requests.FavoriteUpdateRequest;

@Entity
public class Favorite {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "userId")
	private User user;
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "eventId")
	private Event event;
	@Column
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm")
	private Date favoriteDate;
	@Column
	private Date lastModificationDate;

	public Favorite() {

	}

	public Favorite(Integer id, User user, Event event, Date favoriteDate, Date lastModificationDate) {
		super();
		this.id = id;
		this.user = user;
		this.event = event;
		this.favoriteDate = favoriteDate;
		this.lastModificationDate = lastModificationDate;
	}

	public void setFavorite(@Valid FavoriteAddRequest favoriteAddRequest, Event event, User user) {
		this.event = event;
		this.user = user;
		this.favoriteDate = new Date();
		this.lastModificationDate = new Date();
	}

	public void setFavorite(@Valid FavoriteUpdateRequest favoriteUpdateRequest, Event event, User user) {
		this.id = favoriteUpdateRequest.getId();
		this.event = event;
		this.user = user;
		this.favoriteDate = favoriteUpdateRequest.getFavoriteDate();
		this.lastModificationDate = new Date();
	}

	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	public String toString() {
		return "Id: " + id + ", User: " + user + ", Event: " + event + ", Favorite date: " + favoriteDate.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Date getFavoriteDate() {
		return favoriteDate;
	}

	public void setFavoriteDate(Date favoriteDate) {
		this.favoriteDate = favoriteDate;
	}
}
