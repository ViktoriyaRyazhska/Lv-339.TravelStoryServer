package com.travelstory.repositories;

import com.travelstory.entity.Media;
import com.travelstory.entity.TravelStory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    public Media[] findAllByUserIdAndMediaType(Long userId, Media.MediaType mediaType);

    public Media[] findAllByTravelStoryId(long travelStoryId);

    public Page<Media> findAllByTravelStoryIn( List<TravelStory> TravelStory, Pageable pageable);
}
