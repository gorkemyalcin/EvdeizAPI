package com.urlayasam.project.controllers;

import java.text.ParseException;
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

import com.urlayasam.project.exceptions.EventAlreadyExistsException;
import com.urlayasam.project.exceptions.EventNotFoundException;
import com.urlayasam.project.exceptions.EventUpdateRequestHasNullValuesException;
import com.urlayasam.project.exceptions.NullParameterException;
import com.urlayasam.project.models.Event;
import com.urlayasam.project.models.EventType;
import com.urlayasam.project.requests.EventAddRequest;
import com.urlayasam.project.requests.EventUpdateRequest;
import com.urlayasam.project.services.EventService;

@RestController
@RequestMapping("/events")
@CrossOrigin
public class EventController {

	@Autowired
	private EventService eventService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getEvents(@PathVariable("id") Integer id) {
		Event event;
		try {
			event = eventService.findEventById(id);
		} catch (EventNotFoundException | NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return new ResponseEntity<>(event, HttpStatus.ACCEPTED);
	}

	@GetMapping
	public ResponseEntity<?> getEventsWithOptionalParameters(@RequestParam(required = false) Integer id,
			@RequestParam(required = false) String name, @RequestParam(required = false) EventType type,
			@RequestParam(required = false) String place, @RequestParam(required = false) String address,
			@RequestParam(required = false) Date startDate, @RequestParam(required = false) Date startTime,
			@RequestParam(required = false) Date endDate, @RequestParam(required = false) Date endTime,
			@RequestParam(required = false) String description, @RequestParam(required = false) Date registrationDate,
			@RequestParam(required = false) String festivalName, @RequestParam(required = false) String companyName,
			@RequestParam(required = false) String longitude, @RequestParam(required = false) String latitude, @RequestParam(required = false) Boolean chooseActiveEvents) {
		List<Event> eventList;
		try {
			eventList = eventService.getEventsWithOptionalParams(id, name, type, place, address, startDate, startTime,
					endDate, endTime, description, registrationDate, festivalName, companyName, longitude, latitude, chooseActiveEvents);
		} catch (EventNotFoundException | ParseException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(eventList);

	}

	@PostMapping
	public ResponseEntity<?> addEvent(@Valid @RequestBody EventAddRequest eventAddRequest) {
		try {
			System.out.println(eventAddRequest);
			eventService.add(eventAddRequest);
		} catch (EventAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body("Event with name: " + eventAddRequest.getName() + " has been added.");
	}

	@PutMapping
	public ResponseEntity<?> updateEvent(@Valid @RequestBody EventUpdateRequest eventUpdateRequest) {
		try {
			eventService.update(eventUpdateRequest);
		} catch (EventNotFoundException | NullParameterException | EventUpdateRequestHasNullValuesException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return new ResponseEntity<>("Succesfully updated the event: " + eventUpdateRequest.getName(),
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEvent(@PathVariable("id") Integer eventId) {
		try {
			eventService.delete(eventId);
		} catch (EventNotFoundException | NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return new ResponseEntity<>("Succesfully deleted the event with id: " + eventId, HttpStatus.ACCEPTED);
	}
}
