package com.travelstory.dto.converter;

import com.travelstory.dto.MediaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.travelstory.entity.Media;
import java.util.ArrayList;
import java.util.List;
import com.travelstory.entity.TravelStory;
import org.springframework.stereotype.Component;

@Component
public class MediaConverter implements GeneralConverter<MediaDTO, Media> {
    @Autowired
    private ModelMapper modelMapper;

    public List<Media> convertToEntity(List<MediaDTO> dtos, TravelStory travelStory) {
        List<Media> mediaList = new ArrayList<>();
        for (MediaDTO mediaDTO : dtos) {
            Media media = modelMapper.map(mediaDTO, Media.class);
            media.setTravelStory(travelStory);
            mediaList.add(media);
        }
        return mediaList;
    }

    @Override
    public Media convertToEntity(MediaDTO dto) {
        return null;
    }

    @Override
    public MediaDTO convertToDto(Media entity) {
        return null;
    }
}
