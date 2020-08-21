package com.urlayasam.project.requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FavoriteUpdateRequest {
	private Integer id;
	private Integer userId;
	private Integer eventId;
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm")
	private Date favoriteDate;

	public FavoriteUpdateRequest() {

	}

	public FavoriteUpdateRequest(Integer id, Integer userId, Integer eventId, Date favoriteDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.eventId = eventId;
		this.favoriteDate = favoriteDate;
	}
	
	public String toString() {
		return "Id: " + id + ", User id: " + userId + ", Event id: " + eventId + ", Favorite date: " + favoriteDate.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Date getFavoriteDate() {
		return favoriteDate;
	}

	public void setFavoriteDate(Date favoriteDate) {
		this.favoriteDate = favoriteDate;
	}
}
