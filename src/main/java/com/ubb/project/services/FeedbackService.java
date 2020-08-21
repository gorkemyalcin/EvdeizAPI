package com.urlayasam.project.services;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urlayasam.project.exceptions.FeedbackNotFoundException;
import com.urlayasam.project.exceptions.NullParameterException;
import com.urlayasam.project.models.Feedback;
import com.urlayasam.project.models.User;
import com.urlayasam.project.repositories.FeedbackRepository;
import com.urlayasam.project.repositories.UserRepository;
import com.urlayasam.project.requests.FeedbackAddRequest;
import com.urlayasam.project.requests.FeedbackUpdateRequest;

@Service
public class FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;
	@Autowired
	private UserRepository userRepository;

	public Feedback findFeedbackById(Integer id) throws FeedbackNotFoundException, NullParameterException {
		if (id != null) {
			Feedback feedback = feedbackRepository.findFeedbackById(id);
			if (feedback != null) {
				return feedback;
			} else {
				throw new FeedbackNotFoundException("No feedback was found with id: " + id);
			}
		}
		throw new NullParameterException("Entered id is null.");
	}

	public boolean add(@Valid FeedbackAddRequest paramFeedback) throws NullParameterException {
		if (validateFeedbackAddRequest(paramFeedback)) {
			Feedback feedbackToBeAdded = new Feedback();
			User user = userRepository.findUserById(paramFeedback.getUserId());
			feedbackToBeAdded.setFeedback(paramFeedback, user);
			feedbackRepository.save(feedbackToBeAdded);
			return true;
		} else {
			throw new NullParameterException("The attributes inside the feedback parameter have null values.");
		}
	}

	@Transactional
	public boolean delete(@Valid Integer feedbackId) throws NullParameterException, FeedbackNotFoundException {
		if (feedbackId == null) {
			throw new NullParameterException("Id parameter can not be null.");
		}
		List<Feedback> feedbackList = feedbackRepository.findAll();
		for (Feedback feedback : feedbackList) {
			if (feedback.getId().equals(feedbackId)) {
				feedbackRepository.deleteById(feedbackId);
				return true;
			}
		}
		throw new FeedbackNotFoundException("No feedback was found with id: " + feedbackId);
	}

	public void update(@Valid FeedbackUpdateRequest feedbackUpdateRequest)
			throws FeedbackNotFoundException, NullParameterException {
		if (validateUpdateFeedbackRequest(feedbackUpdateRequest)) {
			Feedback feedback = findFeedbackById(feedbackUpdateRequest.getId());
			if (feedback != null) {
				User user = userRepository.findUserById(feedbackUpdateRequest.getUserId());
				feedback.setFeedback(feedbackUpdateRequest, user);
				feedbackRepository.save(feedback);
			} else {
				throw new FeedbackNotFoundException(
						"Feedback with id: " + feedbackUpdateRequest.getId() + " was not found.");
			}
		} else {
			throw new NullParameterException("The parameters for updating feedback can not be null");
		}
	}

	private boolean validateUpdateFeedbackRequest(FeedbackUpdateRequest feedbackUpdateRequest) {
		return feedbackUpdateRequest != null && feedbackUpdateRequest.getId() != null
				&& feedbackUpdateRequest.getFeedbackDate() != null && feedbackUpdateRequest.getFeedbackText() != null
				&& feedbackUpdateRequest.getUserId() != null;
	}

	private boolean validateFeedbackAddRequest(FeedbackAddRequest feedbackAddRequest) {
		return feedbackAddRequest != null && feedbackAddRequest.getFeedbackText() != null
				&& feedbackAddRequest.getUserId() != null;
	}

	public List<Feedback> findFeedbacksWithOptionalParams(Integer id, Integer userId, String feedback, Date feedbackDate)
			throws FeedbackNotFoundException {
		List<Feedback> feedbackList = feedbackRepository.findAll();
		eliminateWrongFeedbacksWithId(feedbackList, id);
		eliminateWrongFeedbacksWithUser(feedbackList, userId);
		eliminateWrongFeedbacksWithFeedback(feedbackList, feedback);

		eliminateWrongFeedbacksWithFeedbackDate(feedbackList, feedbackDate);

		if (feedbackList.isEmpty()) {
			throw new FeedbackNotFoundException("There is no such feedback with the given parameters.");

		}
		return feedbackList;
	}

	private void eliminateWrongFeedbacksWithId(List<Feedback> feedbackList, Integer id) {
		if (id != null) {
			Iterator<Feedback> feedbackIterator = feedbackList.iterator();
			while (feedbackIterator.hasNext()) {
				Feedback nextFeedback = feedbackIterator.next();
				if (!nextFeedback.getId().equals(id)) {
					feedbackIterator.remove();
				}
			}
		}
	}

	private void eliminateWrongFeedbacksWithUser(List<Feedback> feedbackList, Integer userId) {
		if (userId != null) {
			Iterator<Feedback> feedbackIterator = feedbackList.iterator();
			while (feedbackIterator.hasNext()) {
				Feedback nextFeedback = feedbackIterator.next();
				if (!nextFeedback.getUser().getId().equals(userId)) {
					feedbackIterator.remove();
				}
			}
		}
	}

	private void eliminateWrongFeedbacksWithFeedback(List<Feedback> feedbackList, String feedback) {
		if (feedback != null) {
			Iterator<Feedback> feedbackIterator = feedbackList.iterator();
			while (feedbackIterator.hasNext()) {
				Feedback nextFeedback = feedbackIterator.next();
				if (!nextFeedback.getFeedbackText().equals(feedback)) {
					feedbackIterator.remove();
				}
			}
		}
	}

	private void eliminateWrongFeedbacksWithFeedbackDate(List<Feedback> feedbackList, Date feedbackDate) {
		if (feedbackDate != null) {
			Iterator<Feedback> feedbackIterator = feedbackList.iterator();
			while (feedbackIterator.hasNext()) {
				Feedback nextFeedback = feedbackIterator.next();
				if (!nextFeedback.getFeedbackDate().equals(feedbackDate)) {
					feedbackIterator.remove();
				}
			}
		}
	}

}
