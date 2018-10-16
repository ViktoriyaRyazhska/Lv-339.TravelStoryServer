package com.travelstory.services;

import com.travelstory.entity.Comment;
import com.travelstory.entity.Media;
import com.travelstory.entity.TravelStory;
import com.travelstory.entity.User;
import com.travelstory.exceptions.ResourceNotFoundException;
import com.travelstory.exceptions.codes.ExceptionCode;
import com.travelstory.repositories.CommentRepository;
import com.travelstory.repositories.MediaRepository;
import com.travelstory.repositories.TravelStoryRepository;
import com.travelstory.repositories.UserRepository;
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
    @Autowired
    UserRepository userRepository;
    @Autowired
    TravelStoryRepository travelStoryRepository;
    @Autowired
    MediaRepository mediaRepository;

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
            throw new ResourceNotFoundException("Comment with that id is not present in database",
                    ExceptionCode.COMMENT_NOT_FOUND);
        }
    }

    @Override
    public List<Comment> getCommentsByMedia(Media media) {

        return commentRepository.findAllByMedia(media);
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

    /**
     * @param comment
     * @param userId
     * @param travelStoryId
     * @param mediaId
     * @return
     */
    @Override
    public Comment add(Comment comment, Long userId, Long travelStoryId, Long mediaId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        User user = optionalUser.orElseThrow(
                () -> new ResourceNotFoundException("no such user in the database", ExceptionCode.USER_NOT_FOUND));
        Optional<TravelStory> travelStoryOptional = travelStoryRepository.findById(travelStoryId);
        TravelStory travelStory = travelStoryOptional
                .orElseThrow(() -> new ResourceNotFoundException("no such travel story in the database",
                        ExceptionCode.TRAVELSTORY_NOT_FOUND));
        Optional<Media> mediaOptional = mediaRepository.findById(mediaId);
        Media media = mediaOptional.orElseThrow(
                () -> new ResourceNotFoundException("no such media in the database", ExceptionCode.MEDIA_NOT_FOUND));
        comment.setUser(user);
        comment.setTravelStory(travelStory);
        comment.setMedia(media);
        return comment;

    }
}
