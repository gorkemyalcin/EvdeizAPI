package com.urlayasam.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.urlayasam.project.models.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, String> {

	Comment findCommentById(Integer id);
	
	List<Comment> findAll();

	void deleteById(Integer id);

}
