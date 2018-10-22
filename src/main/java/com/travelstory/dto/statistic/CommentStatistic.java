package com.travelstory.dto.statistic;

import com.travelstory.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommentStatistic extends JpaRepository<Comment, Long> {
    Long countCommentsByCreatedAtBetween(LocalDateTime begin, LocalDateTime end);

    List<Comment> findCommentsByCreatedAtAfterAndCreatedAtBefore(LocalDateTime begin, LocalDateTime end);

}
