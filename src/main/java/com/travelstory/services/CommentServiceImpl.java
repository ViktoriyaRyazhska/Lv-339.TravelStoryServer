package com.travelstory.services;

import com.travelstory.exceptions.ResourceNotFoundException;
import com.travelstory.dao.CommentRepository;
import com.travelstory.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    public CommentServiceImpl() {
    }

    @Override
    public Comment saveComment(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public List<Comment> getAllComments() {
        return (List<Comment>) commentRepository.findAll();
    }

    @Override
    public List<Comment> getCommentsByTravelStoryId(Long id) {
        return commentRepository.findAllByTravelStoryId(id);
    }

    @Override
    public Comment getComment(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            return comment;
        } else {
            throw new ResourceNotFoundException("Comment", "id", id);
        }
    }

    @Override
    public void addComment(Comment comment) {
        commentRepository.save(comment);

    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);

    }
}
