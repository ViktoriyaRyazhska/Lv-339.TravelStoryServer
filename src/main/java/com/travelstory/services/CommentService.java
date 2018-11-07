package com.travelstory.services;

import com.travelstory.dto.CommentDTO;
import com.travelstory.entity.Comment;
import com.travelstory.entity.MediaType;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    List<CommentDTO> getAllComments(Long contentId, String mediaType);

    CommentDTO add(CommentDTO commentDTO);

    Comment getComment(Long id);

    void deleteComment(Long id);

    Page<CommentDTO> getNext3Comments(Long contentId, String mediaType, int pageNumber);

}
