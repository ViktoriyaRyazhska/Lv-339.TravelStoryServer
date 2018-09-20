package com.travelstory.services;

import com.travelstory.dao.CommentRepository;
import com.travelstory.entity.Comment;
import com.travelstory.entity.Media;
import com.travelstory.entity.TravelStory;
import com.travelstory.entity.User;
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
    public List<Comment> getCommentsByMedia(Media media) {

        return commentRepository.findAllByMedia(media);
    }

    @Override
    public List<Comment> getCommentsByMediaAndParentId(Media media, Long parentId) {
        return commentRepository.findAllByMediaAndParentId(media, parentId);
    }

    @Override
    public List<Comment> getCommentsByUserAndMedia(User user, Media media) {
        return commentRepository.findAllByUserAndMedia(user, media);
    }

    @Override
    public List<Comment> getCommentsByUserAndTravelStory(User user, TravelStory travelStory) {
        return commentRepository.findAllByUserAndTravelStory(user, travelStory);
    }

    @Override
    public List<Comment> getCommentsByTravelStory(TravelStory travelStory) {
        return commentRepository.findAllByTravelStory(travelStory);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);

    }

    @Override
    public void addComment(Comment comment) {
        commentRepository.save(comment);

    }



}
