package com.travelstory.services;

import com.travelstory.dto.CommentDTO;
import com.travelstory.dto.converter.CommentConverter;
import com.travelstory.entity.Comment;
import com.travelstory.entity.Media;
import com.travelstory.exceptions.EntityNotFoundException;
import com.travelstory.repositories.CommentRepository;
import com.travelstory.repositories.MediaRepository;
import com.travelstory.repositories.TravelStoryRepository;
import com.travelstory.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
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

    @Override
    public List<CommentDTO> getNext3Comments(Long travelStoryId, Long mediaId, int pageNumber) {

        if (mediaId == null) {
            return commentConverter.convertToDto(commentRepository
                    .findAllByTravelStoryIdOrderByCreatedAtDesc(travelStoryId, PageRequest.of(pageNumber, 3)));
        } else {

            return commentConverter
                    .convertToDto(commentRepository.findAllByMediaId(mediaId, PageRequest.of(pageNumber, 3)));
        }
    }
}
