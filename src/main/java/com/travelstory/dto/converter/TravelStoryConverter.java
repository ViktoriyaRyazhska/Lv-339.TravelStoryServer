package com.travelstory.dto.converter;

import com.travelstory.dto.TravelStoryDTO;
import com.travelstory.entity.TravelStory;
import com.travelstory.entity.TravelStoryStatus;
import com.travelstory.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TravelStoryConverter implements GeneralConverter<TravelStoryDTO, TravelStory> {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public TravelStory convertToEntity(TravelStoryDTO dto) {
        TravelStory travelStory = modelMapper.map(dto, TravelStory.class);
        travelStory.setCreatedDate(LocalDateTime.now());
        travelStory.setTravelStoryStatus(TravelStoryStatus.STATUS_ACTIVE);
        travelStory.setUserOwner(userRepository.findUserById(dto.getUserId()));
        return travelStory;
    }

    @Override
    public TravelStoryDTO convertToDto(TravelStory entity) {
        return null;
    }

    public TravelStory convertToEntity(TravelStoryDTO travelStoryDTO, TravelStory travelStory) {
        travelStory.setHead(travelStoryDTO.getHead());
        travelStory.setDescription(travelStoryDTO.getDescription());
        travelStory.setUserOwner(userRepository.findUserById(travelStoryDTO.getUserId()));
        travelStory.setUpdatedDate(LocalDateTime.now());
        travelStory.setTravelStoryStatus(TravelStoryStatus.STATUS_ACTIVE);
        return travelStory;
    }

    @Override
    public List<TravelStoryDTO> convertToDto(List<TravelStory> entities) {
        List<TravelStoryDTO> travelStoryDTOS = new ArrayList<>();
        for (TravelStory travelStory : entities) {
            TravelStoryDTO map = modelMapper.map(travelStory, TravelStoryDTO.class);
            travelStoryDTOS.add(map);
        }
        return travelStoryDTOS;
    }
}
