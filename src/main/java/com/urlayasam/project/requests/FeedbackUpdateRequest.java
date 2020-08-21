package com.urlayasam.project.requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FeedbackUpdateRequest {
	private Integer id;
	private Integer userId;
	private String feedbackText;
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm")
	private Date feedbackDate;

	public FeedbackUpdateRequest() {

	}

	public FeedbackUpdateRequest(Integer id, Integer userId, String feedbackText, Date feedbackDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.feedbackText = feedbackText;
		this.feedbackDate = feedbackDate;
	}

	public String toString() {
		return "Id: " + id + ", User id: " + userId + ", Feedback: " + feedbackText + ", Feedback date: "
				+ feedbackDate.toString();
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

	public String getFeedbackText() {
		return feedbackText;
	}

	public void setFeedbackText(String feedbackText) {
		this.feedbackText = feedbackText;
	}

	public Date getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(Date feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

}
