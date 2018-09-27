package com.travelstory.dao;


import com.travelstory.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDAO extends JpaRepository<Comment, Long> {

}
