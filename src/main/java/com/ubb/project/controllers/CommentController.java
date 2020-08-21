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

import com.urlayasam.project.exceptions.CommentNotFoundException;
import com.urlayasam.project.exceptions.NullParameterException;
import com.urlayasam.project.exceptions.UserOrEventNotFoundException;
import com.urlayasam.project.models.Comment;
import com.urlayasam.project.requests.CommentAddRequest;
import com.urlayasam.project.requests.CommentUpdateRequest;
import com.urlayasam.project.services.CommentService;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {

	@Autowired
	private CommentService commentService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getComments(@PathVariable("id") Integer id) {
		Comment comment;
		try {
			comment = commentService.findCommentById(id);
		} catch (CommentNotFoundException | NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return new ResponseEntity<>(comment, HttpStatus.ACCEPTED);
	}

	@GetMapping
	public ResponseEntity<?> getCommentsWithOptionalParameters(@RequestParam(required = false) Integer id,
			@RequestParam(required = false) Integer userId, @RequestParam(required = false) Integer eventId,
			@RequestParam(required = false) String comment, @RequestParam(required = false) Date commentDate) {
		List<Comment> commentList;
		try {
			commentList = commentService.findCommentsWithOptionalParams(id, userId, eventId, comment, commentDate);
		} catch (CommentNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(commentList);

	}

	@PostMapping
	public ResponseEntity<?> addComment(@Valid @RequestBody CommentAddRequest commentAddRequest) {
		try {
			commentService.add(commentAddRequest);
		} catch (NullParameterException | UserOrEventNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body("Comment: " + commentAddRequest.getComment() + " has been added.");
	}

	@PutMapping
	public ResponseEntity<?> updateComment(@Valid @RequestBody CommentUpdateRequest commentUpdateRequest)   {
		try {
			commentService.update(commentUpdateRequest);
		} catch (CommentNotFoundException | NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return new ResponseEntity<>("Succesfully updated the comment with id: " + commentUpdateRequest.getId(),
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteComment(@Valid @RequestBody Integer commentId) {
		try {
			commentService.delete(commentId);
		} catch (CommentNotFoundException | NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return new ResponseEntity<>("Succesfully deleted the comment with id: " + commentId, HttpStatus.ACCEPTED);
	}
}
