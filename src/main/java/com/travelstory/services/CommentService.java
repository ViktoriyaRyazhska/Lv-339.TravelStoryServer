package com.travelstory.services;

import com.travelstory.dto.CommentDTO;
import com.travelstory.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    Comment saveComment(Comment comment);

    List<CommentDTO> getAllComments(Long travelStoryId, Long mediaId);

    CommentDTO add(CommentDTO commentDTO);

    Comment getComment(Long id);

    void deleteComment(Comment comment);

}
