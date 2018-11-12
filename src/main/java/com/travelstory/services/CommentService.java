package com.travelstory.services;

import com.travelstory.dto.CommentDTO;
import com.travelstory.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    public List<CommentDTO> getAllComments(Long contentId, String mediaType);

    public CommentDTO add(CommentDTO commentDTO);

    public Comment getComment(Long id);

    public void deleteComment(Long id);

    public Page<CommentDTO> getNext3Comments(Long contentId, String mediaType, int pageNumber);

}
