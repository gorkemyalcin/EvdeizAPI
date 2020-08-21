package com.urlayasam.project.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.urlayasam.project.exceptions.FeedbackNotFoundException;
import com.urlayasam.project.exceptions.NullParameterException;
import com.urlayasam.project.models.Feedback;
import com.urlayasam.project.requests.FeedbackAddRequest;
import com.urlayasam.project.requests.FeedbackUpdateRequest;
import com.urlayasam.project.services.FeedbackService;

@RestController
@RequestMapping("/feedbacks")
@CrossOrigin
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getFeedbacks(@PathVariable("id") Integer id) {
		Feedback feedback;
		try {
			feedback = feedbackService.findFeedbackById(id);
		} catch (FeedbackNotFoundException | NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return new ResponseEntity<>(feedback, HttpStatus.ACCEPTED);
	}

	@GetMapping
	public ResponseEntity<?> getFeedbacksWithOptionalParameters(@RequestParam(required = false) Integer id,
			@RequestParam(required = false) Integer userId, @RequestParam(required = false) String feedbackText,
			@RequestParam(required = false) Date feedbackDate) {
		List<Feedback> feedbackList;
		try {
			feedbackList = feedbackService.findFeedbacksWithOptionalParams(id, userId, feedbackText, feedbackDate);
		} catch (FeedbackNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(feedbackList);

	}

	@PostMapping
	public ResponseEntity<?> addFeedback(@Valid @RequestBody FeedbackAddRequest feedbackAddRequest) {
		try {
			feedbackService.add(feedbackAddRequest);
		} catch (NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body("Feedback for the user with id: " + feedbackAddRequest.getUserId() + " has been added.");
	}

	@PutMapping
	public ResponseEntity<?> updateFeedback(@Valid @RequestBody FeedbackUpdateRequest feedbackUpdateRequest) {
		try {
			feedbackService.update(feedbackUpdateRequest);
		} catch (FeedbackNotFoundException | NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return new ResponseEntity<>("Succesfully updated the feedback with id: " + feedbackUpdateRequest.getId(),
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteFeedback(@Valid @RequestBody Integer feedbackId) {
		try {
			feedbackService.delete(feedbackId);
		} catch (FeedbackNotFoundException | NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return new ResponseEntity<>("Succesfully deleted the feedback with id: " + feedbackId, HttpStatus.ACCEPTED);
	}
}
