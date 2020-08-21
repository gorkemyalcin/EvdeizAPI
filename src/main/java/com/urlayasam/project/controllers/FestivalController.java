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

import com.urlayasam.project.exceptions.FestivalAlreadyExistsException;
import com.urlayasam.project.exceptions.FestivalNotFoundException;
import com.urlayasam.project.exceptions.NullParameterException;
import com.urlayasam.project.models.Festival;
import com.urlayasam.project.requests.FestivalAddRequest;
import com.urlayasam.project.requests.FestivalUpdateRequest;
import com.urlayasam.project.services.FestivalService;

@RestController
@RequestMapping("/festivals")
@CrossOrigin
public class FestivalController {

	@Autowired
	private FestivalService festivalService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getFestivals(@PathVariable("id") Integer id) {
		Festival festival;
		try {
			festival = festivalService.findFestivalById(id);
		} catch (FestivalNotFoundException | NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return new ResponseEntity<>(festival, HttpStatus.ACCEPTED);
	}

	@GetMapping
	public ResponseEntity<?> getFestivalsWithOptionalParameters(@RequestParam(required = false) Integer id,
			@RequestParam(required = false) String name, @RequestParam(required = false) String address,
			@RequestParam(required = false) Date startDate, @RequestParam(required = false) Date startTime,
			@RequestParam(required = false) Date endDate, @RequestParam(required = false) Date endTime,
			@RequestParam(required = false) String description, @RequestParam(required = false) Date creationDate) {
		List<Festival> festivalList;
		try {
			festivalList = festivalService.findFestivalsWithOptionalParams(id, name, address, description, startDate,
					startTime, endDate, endTime, creationDate);
		} catch (FestivalNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(festivalList);

	}

	@PostMapping
	public ResponseEntity<?> addFestival(@Valid @RequestBody FestivalAddRequest festivalAddRequest) {
		try {
			festivalService.add(festivalAddRequest);
		} catch (FestivalAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body("Festival with name: " + festivalAddRequest.getName() + " has been added.");
	}

	@PutMapping
	public ResponseEntity<?> updateFestival(@Valid @RequestBody FestivalUpdateRequest festivalUpdateRequest) {
		try {
			festivalService.update(festivalUpdateRequest);
		} catch (FestivalNotFoundException | NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return new ResponseEntity<>("Succesfully updated the festival: " + festivalUpdateRequest.getName(),
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteFestival(@Valid @RequestBody Integer festivalId) {
		try {
			festivalService.delete(festivalId);
		} catch (FestivalNotFoundException | NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return new ResponseEntity<>("Succesfully deleted the festival with id: " + festivalId, HttpStatus.ACCEPTED);
	}
}
