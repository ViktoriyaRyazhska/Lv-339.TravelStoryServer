package com.travelstory.dto;

import com.travelstory.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentStatistic extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT count(id) from comments where MONTH (created_at) =:num", nativeQuery = true)
    Long countCommentsByCreatedAt(@Param("num") Integer numberOfMonth);

}
