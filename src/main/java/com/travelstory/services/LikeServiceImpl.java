package com.travelstory.services;

import com.travelstory.dto.LikeDTO;
import com.travelstory.dto.converter.LikeConverter;
import com.travelstory.entity.Like;
import com.travelstory.entity.Media;
import com.travelstory.entity.TravelStory;
import com.travelstory.entity.User;
import com.travelstory.exceptions.EntityNotFoundException;
import com.travelstory.repositories.LikeRepository;
import com.travelstory.repositories.MediaRepository;
import com.travelstory.repositories.TravelStoryRepository;
import com.travelstory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class LikeServiceImpl implements LikeService {
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    MediaRepository mediaRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TravelStoryRepository travelStoryRepository;
    @Autowired
    LikeConverter likeConverter;

    @Override
    public List<LikeDTO> getLikes(Long travelStoryId, Long mediaId) {
        if (mediaId == null) {
            return likeConverter.convertToDto(likeRepository.findAllByTravelStoryId(travelStoryId));
        } else {
            return likeConverter.convertToDto(likeRepository.findAllByMediaId(mediaId));
        }

    }

    @Override
    public LikeDTO add(LikeDTO likeDTO) {

        Like like = likeConverter.convertToEntity(likeDTO);

        Optional<User> userOptional = userRepository.findById(likeDTO.getLoggedUserId());
        Optional<TravelStory> travelStoryOptional = travelStoryRepository.findById(likeDTO.getTravelStoryId());
        TravelStory travelStory = travelStoryOptional
                .orElseThrow(() -> new EntityNotFoundException("no such travel story in the database",
                        "sorry,we have no such travel story ", TravelStory.class));
        User user = userOptional.orElseThrow(() -> new EntityNotFoundException("no such user in the database",
                "sorry,we have no such user", User.class));
        like.setUser(user);
        like.setTravelStory(travelStory);
        like.setLikeState(likeDTO.isLikeState());
        if (likeDTO.getMediaId() == null) {
            likeRepository.save(like);
        } else {
            Optional<Media> mediaOptional = mediaRepository.findById(likeDTO.getMediaId());
            Media media = mediaOptional.orElseThrow(() -> new EntityNotFoundException("no such media in the database",
                    "sorry,we have no such media ", Media.class));
            like.setMedia(media);
            likeConverter.convertToDto(likeRepository.save(like));

        }
        return likeDTO;
    }

    @Override
    public void deleteLike(Long id) {
        likeRepository.deleteById(id);
    }

    @Override
    public LikeDTO getUserLike(Long travelStoryId, Long mediaId, Long userId) {
        if (mediaId != null) {
            Like like = likeRepository.findByMediaIdAndUserId(mediaId, userId);
            return likeConverter.convertToDto(like);

        } else {
            Like like = likeRepository.findByTravelStoryIdAndUserId(travelStoryId, userId);
            return likeConverter.convertToDto(like);
        }

    }
}
