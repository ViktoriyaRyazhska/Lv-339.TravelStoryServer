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
        return null;
    }

    @Override
    public LikeDTO convertToDto(Like entity) {
//        return likeDTO.setId(entity.getId());
        return null;
    }

    @Override
    public List<Like> convertToEntity(List<LikeDTO> dtos) {
        return null;


    }

    @Override
    public List<LikeDTO> convertToDto(List<Like> entities) {

        List<LikeDTO> likeDTOList = new ArrayList<>();

        for (Like like : entities) {
            LikeDTO likeDTO =new LikeDTO(like.getId());
            likeDTOList.add(likeDTO);
        }
        return likeDTOList;
    }

}

