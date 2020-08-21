package com.urlayasam.project.services;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urlayasam.project.exceptions.FestivalAlreadyExistsException;
import com.urlayasam.project.exceptions.FestivalNotFoundException;
import com.urlayasam.project.exceptions.NullParameterException;
import com.urlayasam.project.models.Festival;
import com.urlayasam.project.repositories.FestivalRepository;
import com.urlayasam.project.requests.FestivalAddRequest;
import com.urlayasam.project.requests.FestivalUpdateRequest;

@Service
public class FestivalService {

	@Autowired
	private FestivalRepository festivalRepository;

	public Festival findFestivalById(Integer id) throws FestivalNotFoundException, NullParameterException {
		if (id != null) {
			Festival festival = festivalRepository.findFestivalById(id);
			if (festival != null) {
				return festival;
			} else {
				throw new FestivalNotFoundException("No festival was found with id: " + id);
			}
		}
		throw new NullParameterException("Entered id is null.");
	}

	public boolean add(@Valid FestivalAddRequest paramFestival)
			throws FestivalAlreadyExistsException, NullParameterException {
		if (validateFestivalAddRequest(paramFestival)) {
			List<Festival> festivalList = festivalRepository.findAll();
			for (Festival festival : festivalList) {
				if (festival.getName().equals(paramFestival.getName())) {
					throw new FestivalAlreadyExistsException(
							"There already exists a festival with name: " + paramFestival.getName());
				}
			}
			Festival festivalToBeAdded = new Festival();
			festivalToBeAdded.setFestival(paramFestival);
			festivalRepository.save(festivalToBeAdded);
			return true;
		} else {
			throw new NullParameterException("The attributes inside the festival parameter have null values.");
		}
	}

	@Transactional
	public boolean delete(@Valid Integer festivalId) throws NullParameterException, FestivalNotFoundException {
		if (festivalId == null) {
			throw new NullParameterException("Id parameter can not be null.");
		}
		List<Festival> festivalList = festivalRepository.findAll();
		for (Festival festival : festivalList) {
			if (festival.getId().equals(festivalId)) {
				festivalRepository.deleteById(festivalId);
				return true;
			}
		}
		throw new FestivalNotFoundException("No festival was found with id: " + festivalId);
	}

	public void update(@Valid FestivalUpdateRequest festivalUpdateRequest)
			throws FestivalNotFoundException, NullParameterException {
		if (validateUpdateFestivalRequest(festivalUpdateRequest)) {
			Festival festival = findFestivalById(festivalUpdateRequest.getId());
			if (festival != null) {
				festival.setFestival(festivalUpdateRequest);
				festivalRepository.save(festival);
			} else {
				throw new FestivalNotFoundException(
						"Festival with id: " + festivalUpdateRequest.getId() + " was not found.");
			}
		} else {
			throw new NullParameterException("The parameters for updating festival can not be null");
		}
	}

	private boolean validateUpdateFestivalRequest(FestivalUpdateRequest festivalUpdateRequest) {
		return festivalUpdateRequest != null && festivalUpdateRequest.getId() != null
				&& festivalUpdateRequest.getName() != null && festivalUpdateRequest.getAddress() != null
				&& festivalUpdateRequest.getDescription() != null && festivalUpdateRequest.getStartDate() != null
				&& festivalUpdateRequest.getStartTime() != null && festivalUpdateRequest.getEndTime() != null
				&& festivalUpdateRequest.getEndDate() != null && festivalUpdateRequest.getCreationDate() != null;
	}

	private boolean validateFestivalAddRequest(FestivalAddRequest festivalAddRequest) {
		return festivalAddRequest != null && festivalAddRequest.getName() != null
				&& festivalAddRequest.getAddress() != null && festivalAddRequest.getDescription() != null
				&& festivalAddRequest.getStartDate() != null && festivalAddRequest.getStartTime() != null
				&& festivalAddRequest.getEndTime() != null && festivalAddRequest.getEndDate() != null;
	}

	public List<Festival> findFestivalsWithOptionalParams(Integer id, String name, String address, String description,
			Date startDate, Date startTime, Date endDate, Date endTime, Date creationDate)
			throws FestivalNotFoundException {
		List<Festival> festivalList = festivalRepository.findAll();
		eliminateWrongFestivalsWithId(festivalList, id);
		eliminateWrongFestivalsWithName(festivalList, name);
		eliminateWrongFestivalsWithAddress(festivalList, address);
		eliminateWrongFestivalsWithDescription(festivalList, description);
		eliminateWrongFestivalsWithStartDate(festivalList, startDate);
		eliminateWrongFestivalsWithStartTime(festivalList, startTime);
		eliminateWrongFestivalsWithEndTime(festivalList, endTime);
		eliminateWrongFestivalsWithEndDate(festivalList, endDate);
		eliminateWrongFestivalsWithCreationDate(festivalList, creationDate);

		if (festivalList.isEmpty()) {
			throw new FestivalNotFoundException("There is no such festival with the given parameters.");

		}
		return festivalList;
	}

	private void eliminateWrongFestivalsWithId(List<Festival> festivalList, Integer id) {
		if (id != null) {
			Iterator<Festival> festivalIterator = festivalList.iterator();
			while (festivalIterator.hasNext()) {
				Festival nextFestival = festivalIterator.next();
				if (!nextFestival.getId().equals(id)) {
					festivalIterator.remove();
				}
			}
		}
	}

	private void eliminateWrongFestivalsWithName(List<Festival> festivalList, String name) {
		if (name != null) {
			Iterator<Festival> festivalIterator = festivalList.iterator();
			while (festivalIterator.hasNext()) {
				Festival nextFestival = festivalIterator.next();
				if (!nextFestival.getName().equals(name)) {
					festivalIterator.remove();
				}
			}
		}
	}

	private void eliminateWrongFestivalsWithAddress(List<Festival> festivalList, String address) {
		if (address != null) {
			Iterator<Festival> festivalIterator = festivalList.iterator();
			while (festivalIterator.hasNext()) {
				Festival nextFestival = festivalIterator.next();
				if (!nextFestival.getAddress().equals(address)) {
					festivalIterator.remove();
				}
			}
		}
	}

	private void eliminateWrongFestivalsWithDescription(List<Festival> festivalList, String description) {
		if (description != null) {
			Iterator<Festival> festivalIterator = festivalList.iterator();
			while (festivalIterator.hasNext()) {
				Festival nextFestival = festivalIterator.next();
				if (!nextFestival.getDescription().equals(description)) {
					festivalIterator.remove();
				}
			}
		}
	}

	private void eliminateWrongFestivalsWithStartDate(List<Festival> festivalList, Date startDate) {
		if (startDate != null) {
			Iterator<Festival> festivalIterator = festivalList.iterator();
			while (festivalIterator.hasNext()) {
				Festival nextFestival = festivalIterator.next();
				if (!nextFestival.getStartDate().equals(startDate)) {
					festivalIterator.remove();
				}
			}
		}
	}

	private void eliminateWrongFestivalsWithStartTime(List<Festival> festivalList, Date startTime) {
		if (startTime != null) {
			Iterator<Festival> festivalIterator = festivalList.iterator();
			while (festivalIterator.hasNext()) {
				Festival nextFestival = festivalIterator.next();
				if (!nextFestival.getStartTime().equals(startTime)) {
					festivalIterator.remove();
				}
			}
		}
	}

	private void eliminateWrongFestivalsWithEndDate(List<Festival> festivalList, Date endDate) {
		if (endDate != null) {
			Iterator<Festival> festivalIterator = festivalList.iterator();
			while (festivalIterator.hasNext()) {
				Festival nextFestival = festivalIterator.next();
				if (!nextFestival.getEndDate().equals(endDate)) {
					festivalIterator.remove();
				}
			}
		}
	}

	private void eliminateWrongFestivalsWithEndTime(List<Festival> festivalList, Date endTime) {
		if (endTime != null) {
			Iterator<Festival> festivalIterator = festivalList.iterator();
			while (festivalIterator.hasNext()) {
				Festival nextFestival = festivalIterator.next();
				if (!nextFestival.getEndTime().equals(endTime)) {
					festivalIterator.remove();
				}
			}
		}
	}

	private void eliminateWrongFestivalsWithCreationDate(List<Festival> festivalList, Date creationDate) {
		if (creationDate != null) {
			Iterator<Festival> festivalIterator = festivalList.iterator();
			while (festivalIterator.hasNext()) {
				Festival nextFestival = festivalIterator.next();
				if (!nextFestival.getCreationDate().equals(creationDate)) {
					festivalIterator.remove();
				}
			}
		}
	}

}
