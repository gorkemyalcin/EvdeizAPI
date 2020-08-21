package com.urlayasam.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.urlayasam.project.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
	User findUserByUsername(String username);

	User findUserById(Integer id);

	List<User> findAll();

	void deleteById(Integer id);

}
