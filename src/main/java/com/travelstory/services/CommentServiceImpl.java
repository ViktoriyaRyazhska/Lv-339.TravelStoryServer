package com.travelstory.services;

import com.travelstory.dto.CommentDTO;
import com.travelstory.dto.converter.CommentConverter;
import com.travelstory.entity.Comment;
import com.travelstory.entity.Media;
import com.travelstory.entity.TravelStory;
import com.travelstory.entity.User;
import com.travelstory.exceptions.EntityNotFoundException;
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
    @Autowired
    CommentConverter commentConverter;

    public CommentServiceImpl() {
    }

    @Override
    public Comment saveComment(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public List<CommentDTO> getAllComments(Long travelStoryId, Long mediaId) {
        if (mediaId != null) {
            return commentConverter.convertToDto(commentRepository.findAllByMediaId(mediaId));
        } else {
            return commentConverter.convertToDto(commentRepository.findAllByTravelStoryId(travelStoryId));
        }
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

    @Override
    public CommentDTO add(CommentDTO commentDTO) {
        Comment comment = commentConverter.convertToEntity(commentDTO);
        if (commentDTO.getMediaId() == null) {
            commentDTO = commentConverter.convertToDto(commentRepository.save(comment));
        } else {
            comment.setMedia(mediaRepository.findById(commentDTO.getMediaId())
                    .orElseThrow(() -> new EntityNotFoundException("no such media in the database",
                            "sorry,we have no such user", Media.class)));
            commentDTO = commentConverter.convertToDto(commentRepository.save(comment));
        }
        return commentDTO;
    }
}
