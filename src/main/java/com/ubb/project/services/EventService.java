package com.urlayasam.project.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urlayasam.project.exceptions.EventAlreadyExistsException;
import com.urlayasam.project.exceptions.EventNotFoundException;
import com.urlayasam.project.exceptions.EventUpdateRequestHasNullValuesException;
import com.urlayasam.project.exceptions.NullParameterException;
import com.urlayasam.project.models.Company;
import com.urlayasam.project.models.Event;
import com.urlayasam.project.models.EventType;
import com.urlayasam.project.models.Festival;
import com.urlayasam.project.repositories.CompanyRepository;
import com.urlayasam.project.repositories.EventRepository;
import com.urlayasam.project.repositories.FestivalRepository;
import com.urlayasam.project.requests.EventAddRequest;
import com.urlayasam.project.requests.EventUpdateRequest;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private FestivalRepository festivalRepository;
	@Autowired
	private CompanyRepository companyRepository;

	public Event findEventByName(String name) throws EventNotFoundException, NullParameterException {
		if (name != null) {
			Event event = eventRepository.findEventByName(name);
			if (event != null) {
				return event;
			}
			throw new EventNotFoundException("No event was found with the name: " + name);
		}
		throw new NullParameterException("Given name parameter is null. ");
	}

	public Event findEventById(Integer id) throws EventNotFoundException, NullParameterException {
		if (id != null) {
			Event event = eventRepository.findEventById(id);
			if (event != null) {
				return event;
			}
			throw new EventNotFoundException("No event was found with the id: " + id);
		}
		throw new NullParameterException("Given eventname parameter is null. ");
	}

	public List<Event> findEvents() throws EventNotFoundException {
		List<Event> eventList = eventRepository.findAll();
		if (eventList.isEmpty() || eventList == null) {
			throw new EventNotFoundException("There are no events to be found.");
		}
		return eventList;
	}

	public boolean update(EventUpdateRequest eventUpdateRequest)
			throws EventNotFoundException, NullParameterException, EventUpdateRequestHasNullValuesException {
		if (validateUpdateEventRequestInput(eventUpdateRequest)) {
			Festival festival = festivalRepository.findFestivalById(eventUpdateRequest.getFestivalId());
			Company company = companyRepository.findCompanyById(eventUpdateRequest.getCompanyId());
			Event event = findEventById(eventUpdateRequest.getId());
			if (event != null) {
				event.setEvent(eventUpdateRequest, festival, company);
				eventRepository.save(event);
				return true;
			}
		}
		throw new EventUpdateRequestHasNullValuesException("Event update request has null values in it");
	}

	@Transactional
	public boolean delete(Integer eventId) throws EventNotFoundException, NullParameterException {
		if (eventId == null) {
			throw new NullParameterException("The id field in the parameter is null.");
		}
		List<Event> eventList = eventRepository.findAll();
		for (Event event : eventList) {
			if (event.getId().equals(eventId)) {
				eventRepository.deleteById(eventId);
				return true;
			}
		}
		throw new EventNotFoundException("No event was found with id: " + eventId);
	}

	public boolean add(@Valid EventAddRequest paramEvent) throws EventAlreadyExistsException, NullParameterException {
		if (validateAddEventRequestInput(paramEvent)) {
			List<Event> eventList = eventRepository.findAll();
			for (Event event : eventList) {
				if (event.getName().equals(paramEvent.getName())) {
					throw new EventAlreadyExistsException(
							"There already exists an event with name: " + paramEvent.getName());
				}
			}
			Event eventToBeAdded = new Event();
			Festival festival = festivalRepository.findFestivalByName(paramEvent.getFestivalName());
			Company company = companyRepository.findCompanyByName(paramEvent.getCompanyName());
			eventToBeAdded.setEvent(paramEvent, festival, company);
			System.out.println(eventToBeAdded);
			eventRepository.save(eventToBeAdded);
			return true;
		} else {
			throw new NullParameterException("The attributes inside the Event parameter have null values.");
		}
	}

	private boolean validateAddEventRequestInput(EventAddRequest eventAddRequest) {
		return (eventAddRequest != null && eventAddRequest.getName() != null && eventAddRequest.getAddress() != null
				&& eventAddRequest.getDescription() != null && eventAddRequest.getEndDate() != null
				&& eventAddRequest.getEndTime() != null && eventAddRequest.getStartTime() != null
				&& eventAddRequest.getPlace() != null && eventAddRequest.getStartDate() != null
				&& eventAddRequest.getType() != null && eventAddRequest.getLatitude() != null
				&& eventAddRequest.getLongitude() != null);
	}

	private boolean validateUpdateEventRequestInput(EventUpdateRequest eventUpdateRequest) {
		return (eventUpdateRequest != null && eventUpdateRequest.getName() != null && eventUpdateRequest.getId() != null
				&& eventUpdateRequest.getAddress() != null && eventUpdateRequest.getDescription() != null
				&& eventUpdateRequest.getEndDate() != null && eventUpdateRequest.getEndTime() != null
				&& eventUpdateRequest.getStartTime() != null && eventUpdateRequest.getPlace() != null
				&& eventUpdateRequest.getStartDate() != null && eventUpdateRequest.getType() != null
				&& eventUpdateRequest.getLatitude() != null && eventUpdateRequest.getLongitude() != null);
	}

	public List<Event> getEventsWithOptionalParams(Integer id, String name, EventType type, String place,
			String address, Date startDate, Date startTime, Date endDate, Date endTime, String description,
			Date registrationDate, String festivalName, String companyName, String longitude, String latitude,
			Boolean chooseActiveEvents) throws EventNotFoundException, ParseException {
		List<Event> eventList = eventRepository.findAll();
		eliminateNotActiveEvents(chooseActiveEvents, eventList);
		eliminateNotEqualId(id, eventList);
		eliminateNotEqualName(name, eventList);
		eliminateNotEqualType(type, eventList);
		eliminateNotEqualPlace(place, eventList);
		eliminateNotEqualAddress(address, eventList);
		eliminateNotEqualStartDate(startDate, eventList);
		eliminateNotEqualStartTime(startTime, eventList);
		eliminateNotEqualEndTime(endTime, eventList);
		eliminateNotEqualEndDate(endDate, eventList);
		eliminateNotEqualDescription(description, eventList);
		eliminateNotEqualRegistrationDate(registrationDate, eventList);
		eliminateNotEqualFestivalName(festivalName, eventList);
		eliminateNotEqualCompanyName(companyName, eventList);
		eliminateNotEqualLongitude(longitude, eventList);
		eliminateNotEqualLatitude(latitude, eventList);
		if (eventList.isEmpty()) {
			throw new EventNotFoundException("There is no such event.");
		}
		sortEventListByTheirStartDate(eventList);
		return eventList;
	}

	private void sortEventListByTheirStartDate(List<Event> eventList) {
		Collections.sort(eventList);
	}

	private void eliminateNotActiveEvents(Boolean chooseActiveEvents, List<Event> eventList) throws ParseException {
		if (chooseActiveEvents != null && chooseActiveEvents.booleanValue()) {
			Date currentDate = new Date();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			SimpleDateFormat dateFormatForDay = new SimpleDateFormat("dd.MM.yyyy");
			SimpleDateFormat dateFormatForTime = new SimpleDateFormat("HH:mm");
			Iterator<Event> eventIterator = eventList.iterator();
			while (eventIterator.hasNext()) {
				Event nextEvent = eventIterator.next();
				Date eventDate = dateFormatter.parse(dateFormatForDay.format(nextEvent.getEndDate()).toString() + " "
						+ dateFormatForTime.format(nextEvent.getEndTime()).toString());
				if (eventDate.before(currentDate)) {
					eventIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualId(Integer id, List<Event> eventList) {
		if (id != null) {
			Iterator<Event> eventIterator = eventList.iterator();
			while (eventIterator.hasNext()) {
				Event nextEvent = eventIterator.next();
				if (!nextEvent.getId().equals(id)) {
					eventIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualName(String name, List<Event> eventList) {
		if (name != null) {
			Iterator<Event> eventIterator = eventList.iterator();
			while (eventIterator.hasNext()) {
				Event nextEvent = eventIterator.next();
				if (!nextEvent.getName().equals(name)) {
					eventIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualType(EventType type, List<Event> eventList) {
		if (type != null) {
			Iterator<Event> eventIterator = eventList.iterator();
			while (eventIterator.hasNext()) {
				Event nextEvent = eventIterator.next();
				if (!nextEvent.getType().equals(type)) {
					eventIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualPlace(String place, List<Event> eventList) {
		if (place != null) {
			Iterator<Event> eventIterator = eventList.iterator();
			while (eventIterator.hasNext()) {
				Event nextEvent = eventIterator.next();
				if (!nextEvent.getPlace().equals(place)) {
					eventIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualAddress(String address, List<Event> eventList) {
		if (address != null) {
			Iterator<Event> eventIterator = eventList.iterator();
			while (eventIterator.hasNext()) {
				Event nextEvent = eventIterator.next();
				if (!nextEvent.getAddress().equals(address)) {
					eventIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualStartDate(Date startDate, List<Event> eventList) {
		if (startDate != null) {
			Iterator<Event> eventIterator = eventList.iterator();
			while (eventIterator.hasNext()) {
				Event nextEvent = eventIterator.next();
				if (!nextEvent.getStartDate().equals(startDate)) {
					eventIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualEndTime(Date endTime, List<Event> eventList) {
		if (endTime != null) {
			Iterator<Event> eventIterator = eventList.iterator();
			while (eventIterator.hasNext()) {
				Event nextEvent = eventIterator.next();
				if (!nextEvent.getEndTime().equals(endTime)) {
					eventIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualStartTime(Date startTime, List<Event> eventList) {
		if (startTime != null) {
			Iterator<Event> eventIterator = eventList.iterator();
			while (eventIterator.hasNext()) {
				Event nextEvent = eventIterator.next();
				if (!nextEvent.getStartTime().equals(startTime)) {
					eventIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualEndDate(Date endDate, List<Event> eventList) {
		if (endDate != null) {
			Iterator<Event> eventIterator = eventList.iterator();
			while (eventIterator.hasNext()) {
				Event nextEvent = eventIterator.next();
				if (!nextEvent.getEndDate().equals(endDate)) {
					eventIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualDescription(String description, List<Event> eventList) {
		if (description != null) {
			Iterator<Event> eventIterator = eventList.iterator();
			while (eventIterator.hasNext()) {
				Event nextEvent = eventIterator.next();
				if (!nextEvent.getDescription().equals(description)) {
					eventIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualRegistrationDate(Date registrationDate, List<Event> eventList) {
		if (registrationDate != null) {
			Iterator<Event> eventIterator = eventList.iterator();
			while (eventIterator.hasNext()) {
				Event nextEvent = eventIterator.next();
				if (!nextEvent.getRegistrationDate().equals(registrationDate)) {
					eventIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualFestivalName(String festivalName, List<Event> eventList) {
		if (festivalName != null) {
			Iterator<Event> eventIterator = eventList.iterator();
			while (eventIterator.hasNext()) {
				Event nextEvent = eventIterator.next();
				if (nextEvent.getFestival() != null && !nextEvent.getFestival().getName().equals(festivalName)) {
					eventIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualCompanyName(String companyName, List<Event> eventList) {
		if (companyName != null) {
			Iterator<Event> eventIterator = eventList.iterator();
			while (eventIterator.hasNext()) {
				Event nextEvent = eventIterator.next();
				if (nextEvent.getFestival() != null && !nextEvent.getCompany().getName().equals(companyName)) {
					eventIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualLongitude(String longitude, List<Event> eventList) {
		if (longitude != null) {
			Iterator<Event> eventIterator = eventList.iterator();
			while (eventIterator.hasNext()) {
				Event nextEvent = eventIterator.next();
				if (!nextEvent.getLongitude().equals(longitude)) {
					eventIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualLatitude(String latitude, List<Event> eventList) {
		if (latitude != null) {
			Iterator<Event> eventIterator = eventList.iterator();
			while (eventIterator.hasNext()) {
				Event nextEvent = eventIterator.next();
				if (!nextEvent.getLatitude().equals(latitude)) {
					eventIterator.remove();
				}
			}
		}
	}
}
