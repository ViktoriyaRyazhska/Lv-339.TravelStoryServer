package com.travelstory.dto.converter;

import com.travelstory.dto.LikeDTO;
import com.travelstory.entity.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LikeConverter implements GeneralConverter<LikeDTO, Like> {
    @Autowired
    LikeDTO likeDTO;

    @Override
    public Like convertToEntity(LikeDTO dto) {
        Like like = new Like();
        like.setId(likeDTO.getId());
        like.setLikeState(likeDTO.isLikeState());
        return like;
    }

    @Override
    public LikeDTO convertToDto(Like entity) {

        LikeDTO likeDTO=new LikeDTO();
        likeDTO.setId(entity.getId());
        likeDTO.setLikeState(entity.isLikeState());
        return  likeDTO;
    }

    @Override
    public List<Like> convertToEntity(List<LikeDTO> dtos) {
        return null;
    }

    @Override
    public List<LikeDTO> convertToDto(List<Like> entities) {

        List<LikeDTO> likeDTOList = new ArrayList<>();

        for (Like like : entities) {
            LikeDTO likeDTO = new LikeDTO();
                    likeDTO.setId(like.getId());
                    likeDTO.setUserId(like.getUser().getId());
                    likeDTO.setLikeState(like.isLikeState());
            likeDTOList.add(likeDTO);
        }
        return likeDTOList;
    }

}

