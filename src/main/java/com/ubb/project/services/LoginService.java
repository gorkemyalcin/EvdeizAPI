package com.urlayasam.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.urlayasam.project.exceptions.NullParameterException;
import com.urlayasam.project.exceptions.UserNotFoundException;
import com.urlayasam.project.exceptions.UsernameAndPasswordDoNotMatchException;
import com.urlayasam.project.models.User;
import com.urlayasam.project.requests.LoginRequest;

@Service
public class LoginService {

	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public boolean login(LoginRequest loginRequest) throws UserNotFoundException, NullParameterException, UsernameAndPasswordDoNotMatchException {
		if (loginRequest != null && loginRequest.getUsername() != null && loginRequest.getPassword() != null) {
			User user = userService.findUserByUsername(loginRequest.getUsername());
			if (user != null) {
				if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
					return true;
				}
				throw new UsernameAndPasswordDoNotMatchException("Given username and password do not match");
			}
			throw new UserNotFoundException("No user was found with the name: " + loginRequest.getUsername());
		}
		throw new NullParameterException("Given request has null values in it. ");
	}
}
