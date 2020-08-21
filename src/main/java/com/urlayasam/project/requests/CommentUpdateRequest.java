package com.urlayasam.project.requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CommentUpdateRequest {
	private Integer id;
	private Integer userId;
	private Integer eventId;
	private String comment;
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm")
	private Date commentDate;
	private Integer likeAmount;

	public CommentUpdateRequest() {

	}

	public CommentUpdateRequest(Integer id, Integer userId, Integer eventId, String comment, Integer likeAmount) {
		super();
		this.id = id;
		this.userId = userId;
		this.eventId = eventId;
		this.comment = comment;
		this.commentDate = new Date();
		this.likeAmount = likeAmount;
	}

	public String toString() {
		return "Id: " + id + ", User id: " + userId + ", Event id " + eventId + ", Comment: " + comment
				+ ", Comment date: " + commentDate.toString() + ", Like amount: " + likeAmount;
	}

	public Integer getLikeAmount() {
		return likeAmount;
	}

	public void setLikeAmount(Integer likeAmount) {
		this.likeAmount = likeAmount;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

}
