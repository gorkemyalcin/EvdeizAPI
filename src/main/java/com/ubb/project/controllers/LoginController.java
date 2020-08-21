package com.urlayasam.project.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urlayasam.project.exceptions.NullParameterException;
import com.urlayasam.project.exceptions.UserNotFoundException;
import com.urlayasam.project.exceptions.UsernameAndPasswordDoNotMatchException;
import com.urlayasam.project.requests.LoginRequest;
import com.urlayasam.project.services.LoginService;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
		try {
			loginService.login(loginRequest);
		} catch (UsernameAndPasswordDoNotMatchException | UserNotFoundException | NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully logged in as " + loginRequest.getUsername());
		
	}
}