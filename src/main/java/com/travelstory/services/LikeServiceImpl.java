package com.travelstory.services;

import com.travelstory.dto.LikeDTO;
import com.travelstory.dto.converter.LikeConverter;
import com.travelstory.entity.Media;
import com.travelstory.exceptions.EntityNotFoundException;
import com.travelstory.repositories.LikeRepository;
import com.travelstory.entity.Like;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class LikeServiceImpl implements LikeService {
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    LikeConverter likeConverter;

    @Override
    public List<LikeDTO> getLikes(Long travelStoryId, Long mediaId) {
        if(mediaId==null){
            ModelMapper modelMapper=new ModelMapper();
            return likeConverter.convertToDto(likeRepository.findAllByTravelStoryId(travelStoryId));
        }
        else{
            return  likeConverter.convertToDto(likeRepository.findAllByMediaId(mediaId));
        }

    }

    @Override
    public List<Like> getLikesByMedia(Media media) {
        return likeRepository.findAllByMedia(media);
    }

    @Override
    public Optional<Like> getLike(Long id) {
        return likeRepository.findById(id);
    }

    @Override
    public void addLike(Like like) {
        likeRepository.save(like);
    }

    @Override
    public void deleteLike(Like like) {
        likeRepository.delete(like);
    }

    @Override
    public void updateLike(Long id) {
        Optional<Like> likeOptional = likeRepository.findById(id);
        if (likeOptional.isPresent()) {
            Like like = likeOptional.get();
            likeRepository.save(like);
        } else {
            throw new EntityNotFoundException("Like with that id is not present in database",
                    "Resource 'like' not found", Like.class);
        }
    }
}
