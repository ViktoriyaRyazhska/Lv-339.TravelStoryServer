package com.travelstory.services;

import com.travelstory.dto.MediaDTO;
import com.travelstory.entity.Media;
import com.travelstory.entity.TravelStory;
import com.travelstory.exceptions.ResourceNotFoundException;
import com.travelstory.exceptions.codes.ExceptionCode;
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
    private UserRepository userRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private TravelStoryRepository travelStoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<MediaDTO> getTravelStoryMedias(Long userId, int page, int size) {
        Page<Media> mediaPage = null;
        Page<MediaDTO> mediaPageDTO = null;
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found", ExceptionCode.USER_NOT_FOUND));
        List<TravelStory> travelStorySList = travelStoryRepository.findByUserOwnerId(userId);
        if (travelStorySList.isEmpty()) {
            return mediaPageDTO;
        }

        mediaPage = mediaRepository.findAllByTravelStoryIn(travelStorySList, PageRequest.of(page, size));
        return mediaPage.map((media) -> modelMapper.map(media, MediaDTO.class));

    }

    @Override
    public Page<MediaDTO> getUserMedias(Long userId, int page, int size) {
        Page<Media> mediaPage = null;
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found", ExceptionCode.USER_NOT_FOUND));
        mediaPage = mediaRepository.findAllByUserId(userId, PageRequest.of(page, size));
        return mediaPage.map((media) -> modelMapper.map(media, MediaDTO.class));
    }

    @Override
    public void deleteMedia(Long id) {
        mediaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Media not found", ExceptionCode.MEDIA_NOT_FOUND));
        mediaRepository.deleteById(id);
    }
}
