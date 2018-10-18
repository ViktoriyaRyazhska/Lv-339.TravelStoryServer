package com.travelstory.services;

import com.travelstory.dto.LikeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LikeService {
    List<LikeDTO> getLikes(Long travelStoryId, Long mediaId);

    LikeDTO add(LikeDTO likeDTO);

    void deleteLike(Long id);

    LikeDTO getUserLike(Long travelStoryId, Long mediaId, Long userId);

}
