package com.travelstory.dto.converter;

import com.travelstory.dto.CommentDTO;
import com.travelstory.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentConverter implements GeneralConverter<CommentDTO, Comment> {
    @Autowired
    CommentDTO commentDTO;
    @Autowired
    Comment comment;

    @Override
    public Comment convertToEntity(CommentDTO dto) {
        return null;
    }

    @Override
    public CommentDTO convertToDto(Comment entity) {
        return null;
    }

    @Override
    public List<Comment> convertToEntity(List<CommentDTO> dtos) {
        return null;
    }

    @Override
    public List<CommentDTO> convertToDto(List<Comment> entities) {
        return null;
    }
}
