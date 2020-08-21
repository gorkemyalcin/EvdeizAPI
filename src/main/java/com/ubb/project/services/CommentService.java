package com.urlayasam.project.services;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urlayasam.project.exceptions.CommentNotFoundException;
import com.urlayasam.project.exceptions.NullParameterException;
import com.urlayasam.project.exceptions.UserOrEventNotFoundException;
import com.urlayasam.project.models.Comment;
import com.urlayasam.project.models.Event;
import com.urlayasam.project.models.User;
import com.urlayasam.project.repositories.CommentRepository;
import com.urlayasam.project.repositories.EventRepository;
import com.urlayasam.project.repositories.UserRepository;
import com.urlayasam.project.requests.CommentAddRequest;
import com.urlayasam.project.requests.CommentUpdateRequest;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EventRepository eventRepository;

	public Comment findCommentById(Integer id) throws CommentNotFoundException, NullParameterException {
		if (id != null) {
			Comment comment = commentRepository.findCommentById(id);
			if (comment != null) {
				return comment;
			} else {
				throw new CommentNotFoundException("No comment was found with id: " + id);
			}
		}
		throw new NullParameterException("Entered id is null.");
	}

	public boolean add(@Valid CommentAddRequest commentAddRequest)
			throws NullParameterException, UserOrEventNotFoundException {
		if (validateCommentAddRequeset(commentAddRequest)) {
			Comment commentToBeAdded = new Comment();
			User user = userRepository.findUserById(commentAddRequest.getUserId());
			Event event = eventRepository.findEventById(commentAddRequest.getEventId());
			if (user != null && event != null) {
				commentToBeAdded.setComment(commentAddRequest, user, event);
				commentRepository.save(commentToBeAdded);
				return true;
			}
			throw new UserOrEventNotFoundException("Given parameter's values do not map to the event or the user");
		} else {
			throw new NullParameterException("The attributes inside the comment parameter have null values.");
		}
	}

	private boolean validateCommentAddRequeset(@Valid CommentAddRequest paramComment) {
		return paramComment != null && paramComment.getComment() != null && paramComment.getEventId() != null
				&& paramComment.getUserId() != null;
	}

	@Transactional
	public void removeCommentById(Integer id) {
		if (id != null) {
			commentRepository.deleteById(id);
		}
	}

	@Transactional
	public boolean delete(@Valid Integer commentId) throws NullParameterException, CommentNotFoundException {
		if (commentId == null) {
			throw new NullParameterException("Id parameter can not be null.");
		}
		List<Comment> commentList = commentRepository.findAll();
		for (Comment comment : commentList) {
			if (comment.getId().equals(commentId)) {
				commentRepository.deleteById(commentId);
				return true;
			}
		}
		throw new CommentNotFoundException("No comment was found with id: " + commentId);
	}

	public void update(@Valid CommentUpdateRequest commentUpdateRequest)
			throws CommentNotFoundException, NullParameterException {
		if (validateUpdateCommentRequest(commentUpdateRequest)) {
			Comment comment = findCommentById(commentUpdateRequest.getId());
			if (comment != null) {
				User user = userRepository.findUserById(commentUpdateRequest.getUserId());
				Event event = eventRepository.findEventById(commentUpdateRequest.getEventId());
				comment.setComment(commentUpdateRequest, user, event);
				commentRepository.save(comment);
			} else {
				throw new CommentNotFoundException(
						"Comment with id: " + commentUpdateRequest.getId() + " was not found.");
			}
		} else {
			throw new NullParameterException("The parameters for updating comment can not be null");
		}
	}

	private boolean validateUpdateCommentRequest(CommentUpdateRequest commentUpdateRequest) {
		return commentUpdateRequest != null && commentUpdateRequest.getId() != null
				&& commentUpdateRequest.getComment() != null && commentUpdateRequest.getEventId() != null
				&& commentUpdateRequest.getUserId() != null;
	}

	public List<Comment> findCommentsWithOptionalParams(Integer id, Integer userId, Integer eventId, String comment,
			Date commentDate) throws CommentNotFoundException {
		List<Comment> commentList = commentRepository.findAll();
		try {
			addTimeDifferenceToComments(commentList);
		} catch (ParseException e) {
			System.out.println("Couldnt parse somehow tho it should always parse since the data type is default.");
		}
		eliminateWrongCommentsWithId(commentList, id);
		eliminateWrongCommentsWithUserId(commentList, userId);
		eliminateWrongCommentsWithEventId(commentList, eventId);
		eliminateWrongCommentsWithComment(commentList, comment);
		eliminateWrongCommentsWithCommentDate(commentList, commentDate);

		if (commentList.isEmpty()) {
			throw new CommentNotFoundException("There is no such comment with the given parameters.");

		}
		return commentList;
	}

	private void addTimeDifferenceToComments(List<Comment> commentList) throws ParseException {
		for (Comment comment : commentList) {
			Date now = new Date();
			Date secondDate = comment.getCommentDate();
			long diffInMillies = Math.abs(secondDate.getTime() - now.getTime());
			long timeDifference = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			if (timeDifference != 0) {
				comment.setTimeDifference((timeDifference) + " days");
				if (timeDifference > 6) {
					comment.setTimeDifference((timeDifference / 7) + " weeks");
					if (timeDifference > 28) {
						comment.setTimeDifference((timeDifference / 28) + " months");
						if (timeDifference > 365) {
							comment.setTimeDifference((timeDifference / 365) + " years");
						}
					}
				}
			} else {
				timeDifference = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				System.out.println(timeDifference + "here");
				if (timeDifference != 0) {
					comment.setTimeDifference((timeDifference) + " seconds");
					if (timeDifference > 60) {
						comment.setTimeDifference((timeDifference / 60) + " minutes");
						if (timeDifference > 3600) {
							comment.setTimeDifference((timeDifference) + " hours");
						}
					}

				}
			}
		}
	}

	private void eliminateWrongCommentsWithId(List<Comment> commentList, Integer id) {
		if (id != null) {
			Iterator<Comment> commentIterator = commentList.iterator();
			while (commentIterator.hasNext()) {
				Comment nextComment = commentIterator.next();
				if (!nextComment.getId().equals(id)) {
					commentIterator.remove();
				}
			}
		}
	}

	private void eliminateWrongCommentsWithUserId(List<Comment> commentList, Integer userId) {
		if (userId != null) {
			Iterator<Comment> commentIterator = commentList.iterator();
			while (commentIterator.hasNext()) {
				Comment nextComment = commentIterator.next();
				if (!nextComment.getUser().getId().equals(userId)) {
					commentIterator.remove();
				}
			}
		}
	}

	private void eliminateWrongCommentsWithEventId(List<Comment> commentList, Integer eventId) {
		if (eventId != null) {
			Iterator<Comment> commentIterator = commentList.iterator();
			while (commentIterator.hasNext()) {
				Comment nextComment = commentIterator.next();
				if (!nextComment.getEvent().getId().equals(eventId)) {
					commentIterator.remove();
				}
			}
		}
	}

	private void eliminateWrongCommentsWithComment(List<Comment> commentList, String comment) {
		if (comment != null) {
			Iterator<Comment> commentIterator = commentList.iterator();
			while (commentIterator.hasNext()) {
				Comment nextComment = commentIterator.next();
				if (!nextComment.getComment().equals(comment)) {
					commentIterator.remove();
				}
			}
		}
	}

	private void eliminateWrongCommentsWithCommentDate(List<Comment> commentList, Date commentDate) {
		if (commentDate != null) {
			Iterator<Comment> commentIterator = commentList.iterator();
			while (commentIterator.hasNext()) {
				Comment nextComment = commentIterator.next();
				if (!nextComment.getCommentDate().equals(commentDate)) {
					commentIterator.remove();
				}
			}
		}
	}

}
