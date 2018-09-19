package com.travelstory.services;

import com.travelstory.exceptions.EntityNotFoundException;
import com.travelstory.dao.LikeRepository;
import com.travelstory.entity.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    LikeRepository likeRepository;

    public LikeServiceImpl() {
    }

    @Override
    public List<Like> getAllLikes() {
        return (List<Like>) likeRepository.findAll();
    }

    @Override
    public List<Like> getLikesByContentId(Long id) {
        return (List<Like>) likeRepository.findAllByContentId(id);
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
    public void deleteLike(Long id) {
        likeRepository.deleteById(id);
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
