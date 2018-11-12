package com.travelstory.dto.converter;

import com.travelstory.dto.LikeDTO;
import com.travelstory.entity.Like;
import com.travelstory.entity.MediaType;
import com.travelstory.exceptions.ResourceNotFoundException;
import com.travelstory.exceptions.codes.ExceptionCode;
import com.travelstory.repositories.MediaRepository;
import com.travelstory.repositories.TravelStoryRepository;
import com.travelstory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class LikeConverter implements GeneralConverter<LikeDTO, Like> {
    @Autowired
    private LikeDTO likeDTO;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TravelStoryRepository travelStoryRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Override
    public Like convertToEntity(LikeDTO dto) {
        Like like = new Like();
        like.setUser(userRepository.findById(dto.getUserId()).orElseThrow(
                () -> new ResourceNotFoundException("no such user in the database", ExceptionCode.USER_NOT_FOUND)));
        if (dto.getMediaType().equals(MediaType.MEDIA)) {
            like.setMedia(mediaRepository.findById(dto.getContentId())
                    .orElseThrow(() -> new ResourceNotFoundException("no such media in the database",
                            ExceptionCode.MEDIA_NOT_FOUND)));
        }
        if (dto.getMediaType().equals(MediaType.TRAVELSTORY)) {
            like.setTravelStory(travelStoryRepository.findById(dto.getContentId())
                    .orElseThrow(() -> new ResourceNotFoundException("no such travelStory in the database",
                            ExceptionCode.TRAVELSTORY_NOT_FOUND)));
        }
        like.setCreatedAt(LocalDateTime.now());
        return like;
    }

    @Override
    public LikeDTO convertToDto(Like entity) {

        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setId(entity.getId());
        likeDTO.setUserId(entity.getUser().getId());
        return likeDTO;
    }

    @Override
    public List<Like> convertToEntity(List<LikeDTO> dtos) {
        return null;
    }

    @Override
    public List<LikeDTO> convertToDto(List<Like> entities) {

        List<LikeDTO> likeDTOList = new ArrayList<>();

        for (Like like : entities) {
            LikeDTO likeDTO = new LikeDTO();
            likeDTO.setId(like.getId());
            likeDTO.setUserId(like.getUser().getId());
            likeDTOList.add(likeDTO);
        }
        return likeDTOList;
    }

}
