package com.travelstory.repositories;

import com.travelstory.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findAllByTravelStoryId(Long travelStoryId);

    List<Like> findAllByMediaId(Long mediaId);

    Like findByMediaIdAndUserId(Long mediaId, Long userId);

    Like findByTravelStoryIdAndUserId(Long mediaId, Long userId);

}
