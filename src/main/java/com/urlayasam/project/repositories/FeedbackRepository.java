package com.urlayasam.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.urlayasam.project.models.Feedback;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, String> {

	Feedback findFeedbackById(Integer id);
	
	List<Feedback> findAll();

	void deleteById(Integer id);
}
