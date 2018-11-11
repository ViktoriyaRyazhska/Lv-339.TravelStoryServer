package com.travelstory.services;

import com.travelstory.dto.MediaDTO;
import org.springframework.data.domain.Page;

public interface MediaService {
    public Page<MediaDTO> getTravelStoryMedias(Long userId, int page, int size);

    public Page<MediaDTO> getUserMedias(Long userId, int page, int size);

    public void deleteMedia(Long id);
}
