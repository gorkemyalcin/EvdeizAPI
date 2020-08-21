package com.urlayasam.project.requests;

public class CommentAddRequest {
	private Integer userId;
	private Integer eventId;
	private String comment;
	private Integer likeAmount;

	public CommentAddRequest() {

	}

	public CommentAddRequest(Integer userId, Integer eventId, String comment, Integer likeAmount) {
		super();
		this.userId = userId;
		this.eventId = eventId;
		this.comment = comment;
		this.likeAmount = likeAmount;
	}

	public String toString() {
		return "User id: " + userId + ", Event id " + eventId + ", Comment: " + comment + ", Like amount: " + likeAmount;
	}

	public Integer getLikeAmount() {
		return likeAmount;
	}

	public void setLikeAmount(Integer likeAmount) {
		this.likeAmount = likeAmount;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
