package com.travelstory.services;

import com.travelstory.repositories.CommentRepository;
import com.travelstory.repositories.MediaRepository;
import com.travelstory.repositories.TravelStoryRepository;
import com.travelstory.repositories.UserRepository;
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
            throw new EntityNotFoundException("Comment with that id is not present in database",
                    "Resource 'comment' not found", Comment.class);
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
     *
     * @param comment
     * @param userId
     * @param travelStoryId
     * @param mediaId
     * @return
     */
    @Override
    public Comment add(Comment comment, Long userId, Long travelStoryId, Long mediaId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        User user = optionalUser.orElseThrow(() -> new EntityNotFoundException("no such user in the database",
                "sorry,we have no such user", User.class));
        Optional<TravelStory> travelStoryOptional = travelStoryRepository.findById(travelStoryId);
        TravelStory travelStory = travelStoryOptional
                .orElseThrow(() -> new EntityNotFoundException("no such travel story in the database",
                        "sorry,we have no such travel story", TravelStory.class));
        Optional<Media> mediaOptional = mediaRepository.findById(mediaId);
        Media media = mediaOptional.orElseThrow(() -> new EntityNotFoundException("no such media in the database",
                "sorry,we have no such media", Media.class));
        comment.setUser(user);
        comment.setTravelStory(travelStory);
        comment.setMedia(media);
        return comment;

    }
}
