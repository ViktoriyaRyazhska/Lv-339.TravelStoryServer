package com.travelstory.repositories;

import com.travelstory.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    public Media[] findAllByUserIdAndMediaType(Long userId, Media.MediaType mediaType);
}
