package com.travelstory.services;

import com.travelstory.dto.CommentDTO;
import com.travelstory.dto.converter.CommentConverter;
import com.travelstory.entity.Comment;
import com.travelstory.exceptions.ResourceNotFoundException;
import com.travelstory.exceptions.codes.ExceptionCode;
import com.travelstory.repositories.CommentRepository;
import com.travelstory.repositories.MediaRepository;
import com.travelstory.repositories.TravelStoryRepository;
import com.travelstory.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

//    @Override
//    public Comment saveComment(Comment comment) {
//        commentRepository.save(comment);
//        return comment;
//    }

    @Override
    public List<CommentDTO> getAllComments(Long contentId, String mediaType) {
        if (mediaType.equals("MEDIA")) {
            List<CommentDTO> commentDTOList = commentConverter
                    .convertToDto(commentRepository.findAllByMediaIdOrderByCreatedAtDesc(contentId));
            return commentDTOList;
        }
        if (mediaType.equals("TRAVELSTORY")) {
            {
                List<CommentDTO> commentDTOList = commentConverter
                        .convertToDto(commentRepository.findAllByTravelStoryIdOrderByCreatedAt(contentId));
                return commentDTOList;
            }
        } else {
            throw new ResourceNotFoundException("Comment with that id is not present in database",
                    ExceptionCode.UNSUPPORTED_MEDIA_TYPE);
        }
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
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public CommentDTO add(CommentDTO commentDTO) {
        Comment like = commentConverter.convertToEntity(commentDTO);
        commentRepository.save(like);
        return commentDTO;
    }

    @Override
    public Page<CommentDTO> getNext3Comments(Long contentId, String mediaType, int pageNumber) {

        if (mediaType.equals("MEDIA")) {
            Page<Comment> commentPage =commentRepository.findAllByMediaIdOrderByCreatedAtAsc(contentId, PageRequest.of(pageNumber, 3));
            Page<CommentDTO> commentDTOPage = commentPage.map(comment -> commentConverter.convertToDto(comment));
            return commentDTOPage;

        }
        if (mediaType.equals("TRAVELSTORY")) {
            { Page<Comment> commentPage =commentRepository.findAllByTravelStoryIdOrderByCreatedAtDesc(contentId, PageRequest.of(pageNumber, 3));
            Page<CommentDTO> commentDTOPage = commentPage.map(comment -> commentConverter.convertToDto(comment));
            return commentDTOPage;

            }

        } else {
            throw new ResourceNotFoundException("Unsuported media type",
                    ExceptionCode.UNSUPPORTED_MEDIA_TYPE);
        }
    }
}
