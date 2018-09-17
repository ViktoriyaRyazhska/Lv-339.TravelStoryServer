package com.travelstory.services;

import com.travelstory.entity.Like;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface LikeService {
    public List<Like> getAllLikes();

    public List<Like> getLikesByTravelStoryId(Long id);

    public Optional<Like> getLike(Long id);

    public void addLike(Like like);

    public void deleteLike(Long id);

    public void updateLike(Long id);
}
