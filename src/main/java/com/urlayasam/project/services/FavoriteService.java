package com.urlayasam.project.services;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urlayasam.project.exceptions.EventOrUserDoesNotExistException;
import com.urlayasam.project.exceptions.FavoriteNotFoundException;
import com.urlayasam.project.exceptions.NullParameterException;
import com.urlayasam.project.models.Event;
import com.urlayasam.project.models.Favorite;
import com.urlayasam.project.models.User;
import com.urlayasam.project.repositories.EventRepository;
import com.urlayasam.project.repositories.FavoriteRepository;
import com.urlayasam.project.repositories.UserRepository;
import com.urlayasam.project.requests.FavoriteAddRequest;
import com.urlayasam.project.requests.FavoriteUpdateRequest;

@Service
public class FavoriteService {

	@Autowired
	private FavoriteRepository favoriteRepository;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private UserRepository userRepository;

	public Favorite findFavoriteById(Integer id) throws FavoriteNotFoundException, NullParameterException {
		if (id != null) {
			Favorite favorite = favoriteRepository.findFavoriteById(id);
			if (favorite != null) {
				return favorite;
			} else {
				throw new FavoriteNotFoundException("No favorite was found with id: " + id);
			}
		}
		throw new NullParameterException("Entered id is null.");
	}

	public boolean add(@Valid FavoriteAddRequest paramFavorite)
			throws NullParameterException, EventOrUserDoesNotExistException {
		if (validateFavoriteAddRequeset(paramFavorite)) {
			Favorite favoriteToBeAdded = new Favorite();
			Event event = eventRepository.findEventById(paramFavorite.getEventId());
			User user = userRepository.findUserById(paramFavorite.getUserId());
			if (user != null && event != null) {
				favoriteToBeAdded.setFavorite(paramFavorite, event, user);
				favoriteRepository.save(favoriteToBeAdded);
				return true;
			}
			throw new EventOrUserDoesNotExistException("Couldn't find an event OR a user with the given parameter");
		} else {
			throw new NullParameterException("The attributes inside the favorite parameter have null values.");
		}
	}

	private boolean validateFavoriteAddRequeset(@Valid FavoriteAddRequest paramFavorite) {
		return paramFavorite != null && paramFavorite.getEventId() != null && paramFavorite.getUserId() != null;
	}

	private boolean validateFavoriteUpdateRequeset(@Valid FavoriteUpdateRequest paramFavorite) {
		return paramFavorite != null && paramFavorite.getId() != null && paramFavorite.getEventId() != null
				&& paramFavorite.getUserId() != null && paramFavorite.getFavoriteDate() != null;
	}

	@Transactional
	public boolean delete(@Valid Integer favoriteId) throws NullParameterException, FavoriteNotFoundException {
		if (favoriteId == null) {
			throw new NullParameterException("Id parameter can not be null.");
		}
		List<Favorite> favoriteList = favoriteRepository.findAll();
		for (Favorite favorite : favoriteList) {
			if (favorite.getId().equals(favoriteId)) {
				favoriteRepository.deleteById(favoriteId);
				return true;
			}
		}
		throw new FavoriteNotFoundException("No favorite was found with id: " + favoriteId);
	}

	public void update(@Valid FavoriteUpdateRequest favoriteUpdateRequest)
			throws FavoriteNotFoundException, NullParameterException, EventOrUserDoesNotExistException {
		if (validateFavoriteUpdateRequeset(favoriteUpdateRequest)) {
			Favorite favorite = findFavoriteById(favoriteUpdateRequest.getId());
			Event event = eventRepository.findEventById(favoriteUpdateRequest.getEventId());
			User user = userRepository.findUserById(favoriteUpdateRequest.getUserId());
			if (user != null && event != null) {
				favorite.setFavorite(favoriteUpdateRequest, event, user);
				favoriteRepository.save(favorite);
				return;
			}
			throw new EventOrUserDoesNotExistException("Couldn't find an event or a user with the given parameter");
		} else

		{
			throw new NullParameterException("The parameters for updating favorite can not be null");
		}
	}

	public List<Favorite> findFavoritesWithOptionalParams(Integer id, Integer userId, Integer eventId,
			Date favoriteDate) throws FavoriteNotFoundException {
		List<Favorite> favoriteList = favoriteRepository.findAll();
		eliminateWrongFavoritesWithId(favoriteList, id);
		eliminateWrongFavoritesWithUserId(favoriteList, userId);
		eliminateWrongFavoritesWithEventId(favoriteList, eventId);
		eliminateWrongFavoritesWithFavoriteDate(favoriteList, favoriteDate);

		if (favoriteList.isEmpty()) {
			throw new FavoriteNotFoundException("There is no such favorite with the given parameters.");

		}
		return favoriteList;
	}

	private void eliminateWrongFavoritesWithId(List<Favorite> favoriteList, Integer id) {
		if (id != null) {
			Iterator<Favorite> favoriteIterator = favoriteList.iterator();
			while (favoriteIterator.hasNext()) {
				Favorite nextFavorite = favoriteIterator.next();
				if (!nextFavorite.getId().equals(id)) {
					favoriteIterator.remove();
				}
			}
		}
	}

	private void eliminateWrongFavoritesWithUserId(List<Favorite> favoriteList, Integer userId) {
		if (userId != null) {
			Iterator<Favorite> favoriteIterator = favoriteList.iterator();
			while (favoriteIterator.hasNext()) {
				Favorite nextFavorite = favoriteIterator.next();
				if (!nextFavorite.getUser().getId().equals(userId)) {
					favoriteIterator.remove();
				}
			}
		}
	}

	private void eliminateWrongFavoritesWithEventId(List<Favorite> favoriteList, Integer eventId) {
		if (eventId != null) {
			Iterator<Favorite> favoriteIterator = favoriteList.iterator();
			while (favoriteIterator.hasNext()) {
				Favorite nextFavorite = favoriteIterator.next();
				if (!nextFavorite.getEvent().getId().equals(eventId)) {
					favoriteIterator.remove();
				}
			}
		}
	}

	private void eliminateWrongFavoritesWithFavoriteDate(List<Favorite> favoriteList, Date favoriteDate) {
		if (favoriteDate != null) {
			Iterator<Favorite> favoriteIterator = favoriteList.iterator();
			while (favoriteIterator.hasNext()) {
				Favorite nextFavorite = favoriteIterator.next();
				if (!nextFavorite.getFavoriteDate().equals(favoriteDate)) {
					favoriteIterator.remove();
				}
			}
		}
	}

}
