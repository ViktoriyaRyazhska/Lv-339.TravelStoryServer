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

    public List<Like> getLikesByMedia(Media media);

    public Optional<Like> getLike(Long id);

    public void addLike(Like like);

    public void deleteLike(Like like);

    public void updateLike(Long id);
}
