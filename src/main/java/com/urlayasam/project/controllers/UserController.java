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

import com.urlayasam.project.exceptions.NullParameterException;
import com.urlayasam.project.exceptions.UserAlreadyExistsException;
import com.urlayasam.project.exceptions.UserNotFoundException;
import com.urlayasam.project.models.User;
import com.urlayasam.project.models.UserType;
import com.urlayasam.project.requests.UserAddRequest;
import com.urlayasam.project.requests.UserUpdateRequest;
import com.urlayasam.project.services.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") Integer id) {
		User user;
		try {
			user = userService.findUserById(id);
		} catch (UserNotFoundException | NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
	}

	@GetMapping
	public ResponseEntity<?> getUsersWithOptionalParameters(@RequestParam(required = false) Integer id,
			@RequestParam(required = false) String username, @RequestParam(required = false) String email,
			@RequestParam(required = false) UserType userType,@RequestParam(required = false)  Date creationDate ) {
		List<User> userList;
		try {
			userList = userService.getUsersWithOptionalParams(id, username, email, userType, creationDate);
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userList);

	}

	@PostMapping
	public ResponseEntity<?> addUser(@Valid @RequestBody UserAddRequest paramUser) {
		try {
			userService.add(paramUser);
		} catch (UserAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body("User with username: " + paramUser.getUsername() + " has been added.");
	}

	@PutMapping
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateRequest updateUserRequest) {
		try {
			userService.update(updateUserRequest);
		} catch (UserNotFoundException | NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return new ResponseEntity<>("Succesfully updated the user: " + updateUserRequest.getUsername(),
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteUser(@Valid @RequestBody Integer userId) {
		try {
			userService.delete(userId);
		} catch (UserNotFoundException | NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return new ResponseEntity<>("Succesfully deleted the user with id: " + userId, HttpStatus.ACCEPTED);
	}
}
