package com.urlayasam.project.requests;

public class FeedbackAddRequest {
	private Integer userId;
	private String feedbackText;

	public FeedbackAddRequest() {

	}

	public FeedbackAddRequest(Integer userId, String feedbackText) {
		super();
		this.userId = userId;
		this.feedbackText = feedbackText;
	}

	public String getFeedbackText() {
		return feedbackText;
	}

	public void setFeedbackText(String feedbackText) {
		this.feedbackText = feedbackText;
	}

	public String toString() {
		return "User id: " + userId + ", Feedback: " + feedbackText;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
