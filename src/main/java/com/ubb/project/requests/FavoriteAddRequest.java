package com.urlayasam.project.requests;

public class FavoriteAddRequest {
	private Integer userId;
	private Integer eventId;

	public FavoriteAddRequest() {

	}

	public FavoriteAddRequest(Integer userId, Integer eventId) {
		super();
		this.userId = userId;
		this.eventId = eventId;
	}

	public String toString() {
		return "User id: " + userId + ", Event id: " + eventId;
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
}
