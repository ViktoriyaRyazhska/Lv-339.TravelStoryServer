package com.travelstory.services;

import com.travelstory.dto.LikeDTO;
import com.travelstory.entity.Like;
import com.travelstory.entity.Media;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LikeService {
    public List<LikeDTO> getLikes(Long treavelStoryId, Long mediaId);

    public LikeDTO add(LikeDTO likeDTO);
    public LikeDTO deleteLike(Long id);


    LikeDTO getUserLike(Long travelStoryId, Long mediaId, Long userId);
}
