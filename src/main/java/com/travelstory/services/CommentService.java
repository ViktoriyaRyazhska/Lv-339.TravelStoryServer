package com.travelstory.services;

import com.travelstory.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    public Comment saveComment(Comment comment);

    public List<Comment> getAllComments();

    public List<Comment> getCommentsByTravelStoryId(Long id);

    public Comment getComment(Long id);

    public void addComment(Comment like);

    public void deleteComment(Long id);

}
