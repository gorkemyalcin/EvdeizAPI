package com.urlayasam.project.services;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.urlayasam.project.exceptions.NullParameterException;
import com.urlayasam.project.exceptions.UserAlreadyExistsException;
import com.urlayasam.project.exceptions.UserNotFoundException;
import com.urlayasam.project.models.User;
import com.urlayasam.project.models.UserType;
import com.urlayasam.project.repositories.UserRepository;
import com.urlayasam.project.requests.UserAddRequest;
import com.urlayasam.project.requests.UserUpdateRequest;

@Service
public class UserService {

	@Autowired
	private UserRepository ueUserRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}

	public User findUserByUsername(String username) throws UserNotFoundException, NullParameterException {
		if (username != null) {
			User user = ueUserRepository.findUserByUsername(username);
			if (user != null) {
				return user;
			}
			throw new UserNotFoundException("No user was found with the name: " + username);
		}
		throw new NullParameterException("Given username parameter is null. ");
	}

	public User findUserById(Integer id) throws UserNotFoundException, NullParameterException {
		if (id != null) {
			User user = ueUserRepository.findUserById(id);
			if (user != null) {
				return user;
			}
			throw new UserNotFoundException("No user was found with the id: " + id);
		}
		throw new NullParameterException("Given username parameter is null. ");
	}

	public List<User> findUsers() throws UserNotFoundException {
		List<User> userList = ueUserRepository.findAll();
		if (userList.isEmpty() || userList == null) {
			throw new UserNotFoundException("There are no users to be found.");
		}
		return userList;
	}

	public boolean update(UserUpdateRequest updateUEUserRequest) throws UserNotFoundException, NullParameterException {
		if (validateUpdateUserRequestInput(updateUEUserRequest)) {
			User user = findUserById(updateUEUserRequest.getId());
			user.setUEUser(updateUEUserRequest);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			ueUserRepository.save(user);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean delete(Integer userId) throws UserNotFoundException, NullParameterException {
		if (userId == null) {
			throw new NullParameterException("The id field in the parameter is null.");
		}
		List<User> userList = ueUserRepository.findAll();
		for (User user : userList) {
			if (user.getId().equals(userId)) {
				ueUserRepository.deleteById(userId);
				return true;
			}
		}
		throw new UserNotFoundException("No user was found with id: " + userId);
	}

	public boolean add(@Valid UserAddRequest paramUser) throws UserAlreadyExistsException, NullParameterException {
		if (paramUser.getUsername() != null && paramUser.getPassword() != null) {
			List<User> userList = ueUserRepository.findAll();
			for (User user : userList) {
				if (user.getUsername().equals(paramUser.getUsername())) {
					throw new UserAlreadyExistsException(
							"There already exists a user with name: " + paramUser.getUsername());
				}
				if (user.getEmail().equals(paramUser.getEmail())) {
					throw new UserAlreadyExistsException(
							"There already exists a user with email: " + paramUser.getEmail());
				}
			}
			paramUser.setPassword(passwordEncoder.encode(paramUser.getPassword()));
			User userToBeAdded = new User();
			try {
				userToBeAdded.setUser(paramUser);
			} catch (ParseException e) {
				throw new IllegalArgumentException("Errors occured in the date fields.");
			}
			ueUserRepository.save(userToBeAdded);
			return true;
		} else {
			throw new NullParameterException("The attributes inside User parameter have null values.");
		}
	}

	private boolean validateUpdateUserRequestInput(UserUpdateRequest updateUserRequest) {
		return (updateUserRequest != null && updateUserRequest.getUsername() != null
				&& updateUserRequest.getId() != null && updateUserRequest.getPassword() != null
				&& updateUserRequest.getUserType() != null && updateUserRequest.getEmail() != null);
	}

	public List<User> getUsersWithOptionalParams(Integer id, String username, String email, UserType userType,
			Date creationDate) throws UserNotFoundException {
		List<User> userList = ueUserRepository.findAll();
		eliminateNotEqualId(id, userList);
		eliminateNotEqualUsername(username, userList);
		eliminateNotEqualEmail(email, userList);
		eliminateNotEqualUserType(userType, userList);
		eliminateNotEqualCreationDate(creationDate, userList);

		if (userList.isEmpty()) {
			throw new UserNotFoundException("There is no such user.");
		}
		return userList;
	}

	private void eliminateNotEqualId(Integer id, List<User> userList) {
		if (id != null) {
			Iterator<User> userIterator = userList.iterator();
			while (userIterator.hasNext()) {
				User nextUser = userIterator.next();
				if (!nextUser.getId().equals(id)) {
					userIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualUsername(String username, List<User> userList) {
		if (username != null) {
			Iterator<User> userIterator = userList.iterator();
			while (userIterator.hasNext()) {
				User nextUser = userIterator.next();
				if (!nextUser.getUsername().equals(username)) {
					userIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualEmail(String email, List<User> userList) {
		if (email != null) {
			Iterator<User> userIterator = userList.iterator();
			while (userIterator.hasNext()) {
				User nextUser = userIterator.next();
				if (!nextUser.getEmail().equals(email)) {
					userIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualUserType(UserType userType, List<User> userList) {
		if (userType != null) {
			Iterator<User> userIterator = userList.iterator();
			while (userIterator.hasNext()) {
				User nextUser = userIterator.next();
				if (!nextUser.getUserType().equals(userType)) {
					userIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualCreationDate(Date creationDate, List<User> userList) {
		if (creationDate != null) {
			Iterator<User> userIterator = userList.iterator();
			while (userIterator.hasNext()) {
				User nextUser = userIterator.next();
				if (!nextUser.getCreationDate().equals(creationDate)) {
					userIterator.remove();
				}
			}
		}
	}
}
