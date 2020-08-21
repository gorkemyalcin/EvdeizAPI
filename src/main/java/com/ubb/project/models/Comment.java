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
import com.urlayasam.project.requests.CommentAddRequest;
import com.urlayasam.project.requests.CommentUpdateRequest;

@Entity
public class Comment {
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
	private String comment;
	@Column
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm")
	private Date commentDate;
	@Column
	private Integer likeAmount;
	@Column
	private Date lastModificationDate;
	private String timeDifference;

	public Comment() {

	}

	public Comment(Integer id, User user, Event event, String comment, Date commentDate, Integer likeAmount,
			Date lastModificationDate) {
		super();
		this.id = id;
		this.user = user;
		this.event = event;
		this.comment = comment;
		this.commentDate = commentDate;
		this.likeAmount = likeAmount;
		this.lastModificationDate = lastModificationDate;
	}

	public void setComment(CommentAddRequest commentAddRequest, User user, Event event) {
		this.user = user;
		this.event = event;
		this.comment = commentAddRequest.getComment();
		this.commentDate = new Date();
		this.likeAmount = commentAddRequest.getLikeAmount();
		this.lastModificationDate = new Date();
	}

	public void setComment(@Valid CommentUpdateRequest commentUpdateRequest, User user, Event event) {
		this.id = commentUpdateRequest.getId();
		this.user = user;
		this.event = event;
		this.comment = commentUpdateRequest.getComment();
		this.commentDate = commentUpdateRequest.getCommentDate();
		this.likeAmount = commentUpdateRequest.getLikeAmount();
		this.lastModificationDate = new Date();
	}

	public String toString() {
		return "Id: " + id + ", User: " + user + ", Event: " + event + ", Comment: " + comment + ", Comment date: "
				+ commentDate.toString() + ", Like amount: " + likeAmount;
	}

	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
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

	public String getTimeDifference() {
		return timeDifference;
	}

	public void setTimeDifference(String timeDifference) {
		this.timeDifference = timeDifference;
	}
}
