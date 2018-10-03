package com.travelstory.services;

import com.travelstory.dto.LikeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LikeService {
    public List<LikeDTO> getLikes(Long treavelStoryId, Long mediaId);

    public LikeDTO add(LikeDTO likeDTO);

    public LikeDTO deleteLike(Long id);

    LikeDTO getUserLike(Long travelStoryId, Long mediaId, Long userId);
}
