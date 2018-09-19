package com.travelstory.services;

import com.travelstory.dao.CommentRepository;
import com.travelstory.entity.Comment;
import com.travelstory.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
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
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> getCommentsByContentId(Long id) {
        return commentRepository.findAllByContentId(id);
    }

    @Override
    public Comment getComment(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            return comment;
        } else {
            throw new EntityNotFoundException("Comment with that id is not present in database",
                    "Resource 'comment' not found", Comment.class);
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
