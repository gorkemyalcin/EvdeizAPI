package com.urlayasam.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.urlayasam.project.models.Festival;

@Repository
public interface FestivalRepository extends CrudRepository<Festival, String> {

	Festival findFestivalById(Integer id);
	
	Festival findFestivalByName(String name);

	List<Festival> findAll();

	void deleteById(Integer id);
}
