package com.travelstory.services;

import com.travelstory.dto.LikeDTO;
import com.travelstory.dto.converter.LikeConverter;
import com.travelstory.entity.Like;
import com.travelstory.exceptions.ResourceNotFoundException;
import com.travelstory.exceptions.codes.ExceptionCode;
import com.travelstory.repositories.LikeRepository;
import com.travelstory.repositories.MediaRepository;
import com.travelstory.repositories.TravelStoryRepository;
import com.travelstory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class LikeServiceImpl implements LikeService {

    @Autowired
    LikeRepository likeRepository;
    @Autowired
    MediaRepository mediaRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TravelStoryRepository travelStoryRepository;
    @Autowired
    LikeConverter likeConverter;

    @Override
    public List<LikeDTO> getLikes(Long contentId, String mediaType) {
        if (mediaType.equals("MEDIA")) {
            List<LikeDTO> likeDTOList = likeConverter.convertToDto(likeRepository.findAllByMediaId(contentId));
            return likeDTOList;
        }
        if (mediaType.equals("TRAVELSTORY")) {
            {
                List<LikeDTO> commentDTOList = likeConverter
                        .convertToDto(likeRepository.findAllByTravelStoryId(contentId));
                return commentDTOList;
            }
        } else {
            throw new ResourceNotFoundException("Comment with that id is not present in database",
                    ExceptionCode.UNSUPPORTED_MEDIA_TYPE);
        }
    }

    @Override
    public LikeDTO add(LikeDTO likeDTO) {
        Like like = likeConverter.convertToEntity(likeDTO);
        likeRepository.save(like);
        return likeDTO;
    }

    @Override
    public void deleteLike(Long id) {
        likeRepository.deleteById(id);
    }

    @Override
    public LikeDTO getUserLike(Long travelStoryId, Long mediaId, Long userId) {
        if (mediaId != null) {
            Like like = likeRepository.findByMediaIdAndUserId(mediaId, userId);
            return likeConverter.convertToDto(like);

        } else {
            Like like = likeRepository.findByTravelStoryIdAndUserId(travelStoryId, userId);
            return likeConverter.convertToDto(like);
        }

    }

}
