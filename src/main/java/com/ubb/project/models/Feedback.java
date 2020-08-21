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
import com.urlayasam.project.requests.FeedbackAddRequest;
import com.urlayasam.project.requests.FeedbackUpdateRequest;

@Entity
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "userId")
	private User user;
	@Column
	private String feedbackText;
	@Column
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm")
	private Date feedbackDate;
	@Column
	private Date lastModificationDate;

	public Feedback() {

	}

	public Feedback(Integer id, User user, String feedbackText, Date feedbackDate, Date lastModificationDate) {
		super();
		this.id = id;
		this.user = user;
		this.feedbackText = feedbackText;
		this.feedbackDate = feedbackDate;
		this.lastModificationDate = lastModificationDate;
	}

	public void setComment(FeedbackAddRequest feedbackAddRequest, User user) {
		this.user = user;
		this.feedbackText = feedbackAddRequest.getFeedbackText();
		this.feedbackDate = new Date();
		this.lastModificationDate = new Date();
	}

	public void setComment(@Valid FeedbackUpdateRequest feedbackUpdateRequest, User user) {
		this.id = feedbackUpdateRequest.getId();
		this.user = user;
		this.feedbackText = feedbackUpdateRequest.getFeedbackText();
		this.feedbackDate = feedbackUpdateRequest.getFeedbackDate();
		this.lastModificationDate = new Date();
	}

	public String toString() {
		return "Id: " + id + ", User: " + user + ", Feedback: " + feedbackText + ", Feedback date: "
				+ feedbackDate.toString();
	}

	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	public void setFeedback(FeedbackUpdateRequest feedbackUpdateRequest, User user) {
		this.feedbackDate = feedbackUpdateRequest.getFeedbackDate();
		this.feedbackText = feedbackUpdateRequest.getFeedbackText();
		this.user = user;
	}

	public void setFeedback(FeedbackAddRequest feedbackAddRequest, User user) {
		this.feedbackText = feedbackAddRequest.getFeedbackText();
		this.user = user;
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
