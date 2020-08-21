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

import com.urlayasam.project.exceptions.EventOrUserDoesNotExistException;
import com.urlayasam.project.exceptions.FavoriteNotFoundException;
import com.urlayasam.project.exceptions.NullParameterException;
import com.urlayasam.project.models.Favorite;
import com.urlayasam.project.requests.FavoriteAddRequest;
import com.urlayasam.project.requests.FavoriteUpdateRequest;
import com.urlayasam.project.services.FavoriteService;

@RestController
@RequestMapping("/favorites")
@CrossOrigin
public class FavoriteController {

	@Autowired
	private FavoriteService favoriteService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getFavorites(@PathVariable("id") Integer id) {
		Favorite favorite;
		try {
			favorite = favoriteService.findFavoriteById(id);
		} catch (FavoriteNotFoundException | NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return new ResponseEntity<>(favorite, HttpStatus.ACCEPTED);
	}

	@GetMapping
	public ResponseEntity<?> getFavoritesWithOptionalParameters(@RequestParam(required = false) Integer id,
			@RequestParam(required = false) Integer userId, @RequestParam(required = false) Integer eventId,
			@RequestParam(required = false) Date favoriteDate) {
		List<Favorite> favoriteList;
		try {
			favoriteList = favoriteService.findFavoritesWithOptionalParams(id, userId, eventId, favoriteDate);
		} catch (FavoriteNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(favoriteList);

	}

	@PostMapping
	public ResponseEntity<?> addFavorite(@Valid @RequestBody FavoriteAddRequest favoriteAddRequest) {
		try {
			favoriteService.add(favoriteAddRequest);
		} catch (NullParameterException | EventOrUserDoesNotExistException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body("Favorite for the event with id: " + favoriteAddRequest.getEventId() + " has been added.");
	}

	@PutMapping
	public ResponseEntity<?> updateFavorite(@Valid @RequestBody FavoriteUpdateRequest favoriteUpdateRequest) {
		try {
			favoriteService.update(favoriteUpdateRequest);
		} catch (FavoriteNotFoundException | NullParameterException | EventOrUserDoesNotExistException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return new ResponseEntity<>("Succesfully updated the favorite with id: " + favoriteUpdateRequest.getId(),
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteFavorite(@Valid @RequestBody Integer favoriteId) {
		try {
			favoriteService.delete(favoriteId);
		} catch (FavoriteNotFoundException | NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return new ResponseEntity<>("Succesfully deleted the favorite with id: " + favoriteId, HttpStatus.ACCEPTED);
	}
}
