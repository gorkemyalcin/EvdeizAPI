package com.urlayasam.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.urlayasam.project.models.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, String> {

	Event findEventById(Integer id);

	Event findEventByName(String name);

	List<Event> findAll();

	void deleteById(Integer id);
}
