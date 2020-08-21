package com.urlayasam.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.urlayasam.project.models.Favorite;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, String> {

	Favorite findFavoriteById(Integer id);

	List<Favorite> findAll();

	void deleteById(Integer id);
}
