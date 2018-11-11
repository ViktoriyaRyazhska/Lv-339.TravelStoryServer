package com.travelstory.services;

import com.travelstory.dto.LikeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LikeService {
    public List<LikeDTO> getLikes(Long contentId, String mediaType);

    public LikeDTO add(LikeDTO likeDTO);

    public void deleteLike(Long id);

    public LikeDTO getUserLike(Long travelStoryId, Long mediaId, Long userId);

}
