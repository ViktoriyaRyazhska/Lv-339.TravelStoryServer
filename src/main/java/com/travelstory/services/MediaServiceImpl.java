package com.travelstory.services;

import com.travelstory.dto.MediaDTO;
import com.travelstory.entity.Media;
import com.travelstory.entity.TravelStory;
import com.travelstory.repositories.MediaRepository;
import com.travelstory.repositories.TravelStoryRepository;
import com.travelstory.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private TravelStoryRepository travelStoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<MediaDTO> getUserMedias(Long userId, int page, int size) {
        Page<Media> mediaPage = null;
        Page<MediaDTO> mediaPageDTO = null;
        // userRepository.existsById(userId)
        // .orElseThrow(() -> new ResourceNotFoundException("User not found", ExceptionCode.USER_NOT_FOUND));

        List<TravelStory> travelStorySList = travelStoryRepository.findByUserOwnerId(userId);
        if (travelStorySList.isEmpty()) {
            return mediaPageDTO;
        }

        mediaPage = mediaRepository.findAllByTravelStoryIn(travelStorySList, PageRequest.of(page, size));
        return mediaPage.map((media) -> modelMapper.map(media, MediaDTO.class));

    }
}
