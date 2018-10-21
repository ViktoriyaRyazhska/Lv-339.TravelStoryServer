package com.travelstory.dto.converter;

import com.travelstory.dto.CommentDTO;
import com.travelstory.entity.Comment;
import com.travelstory.exceptions.ResourceNotFoundException;
import com.travelstory.exceptions.codes.ExceptionCode;
import com.travelstory.repositories.TravelStoryRepository;
import com.travelstory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommentConverter implements GeneralConverter<CommentDTO, Comment> {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TravelStoryRepository travelStoryRepository;

    @Override
    public Comment convertToEntity(CommentDTO dto) {
        Comment comment = new Comment();
        comment.setCommentMassage(dto.getCommentMassage());
        comment.setUser(userRepository.findById(dto.getUserId()).orElseThrow(
                () -> new ResourceNotFoundException("no such user in the database", ExceptionCode.USER_NOT_FOUND)));
        comment.setTravelStory(travelStoryRepository.findById(dto.getTravelStoryId())
                .orElseThrow(() -> new ResourceNotFoundException("no such travelStory in the database",
                        ExceptionCode.TRAVELSTORY_NOT_FOUND)));
        comment.setCreatedAt(LocalDateTime.now());
        return comment;

    }

    @Override
    public CommentDTO convertToDto(Comment entity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(entity.getId());
        commentDTO.setUserId(entity.getUser().getId());
        commentDTO.setTravelStoryId(entity.getTravelStory().getId());
        commentDTO.setMediaId(entity.getMedia().getId());
        commentDTO.setUserFirstName(entity.getUser().getFirstName());
        commentDTO.setUserLastName(entity.getUser().getLastName());
        commentDTO.setUserProfilePic(entity.getUser().getProfilePic());
        commentDTO.setCommentMassage(entity.getCommentMassage());
        commentDTO.setCreatedTime(entity.getCreatedAt());
        return commentDTO;
    }

    @Override
    public List<Comment> convertToEntity(List<CommentDTO> dtos) {
        return null;
    }

    @Override
    public List<CommentDTO> convertToDto(List<Comment> entities) {
        List<CommentDTO> listDTO = new ArrayList();
        for (Comment entity : entities) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(entity.getId());
            commentDTO.setUserId(entity.getUser().getId());
            commentDTO.setTravelStoryId(entity.getTravelStory().getId());
            commentDTO.setMediaId(entity.getMedia().getId());
            commentDTO.setUserFirstName(entity.getUser().getFirstName());
            commentDTO.setUserLastName(entity.getUser().getLastName());
            commentDTO.setUserProfilePic(entity.getUser().getProfilePic());
            commentDTO.setCommentMassage(entity.getCommentMassage());
            commentDTO.setCreatedTime(entity.getCreatedAt());
            listDTO.add(commentDTO);
        }
        return listDTO;
    }
}
