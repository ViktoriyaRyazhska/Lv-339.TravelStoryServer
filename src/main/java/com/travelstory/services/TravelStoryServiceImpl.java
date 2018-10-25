package com.travelstory.services;

import com.travelstory.dto.MediaDTO;
import com.travelstory.dto.TravelStoryDTO;
import com.travelstory.entity.Media;
import com.travelstory.entity.TravelStory;
import com.travelstory.entity.TravelStoryStatus;
import com.travelstory.exceptions.ResourceNotFoundException;
import com.travelstory.exceptions.codes.ExceptionCode;
import com.travelstory.repositories.TravelStoryRepository;
import com.travelstory.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TravelStoryServiceImpl implements TravelStoryService {
    @Autowired
    TravelStoryRepository travelStoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public TravelStoryDTO addTravelStory(TravelStoryDTO travelStoryDTO) {
        TravelStory travelStoryBase = new TravelStory();
        travelStoryBase.setHead(travelStoryDTO.getHead());
        travelStoryBase.setDescription(travelStoryDTO.getDescription());
        travelStoryBase.setUserOwner(userRepository.findUserById(travelStoryDTO.getUserId()));
        travelStoryBase.setCreatedDate(LocalDateTime.now());
        travelStoryBase.setTravelStoryStatus(TravelStoryStatus.STATUS_ACTIVE);
        TravelStory travelStorySave = travelStoryRepository.saveAndFlush(travelStoryBase);
        List<MediaDTO> mediaDTOS = travelStoryDTO.getMedia();
        List<Media> mediaList = new ArrayList<>();
        for (MediaDTO mediaDTO : mediaDTOS) {
            Media media = modelMapper.map(mediaDTO, Media.class);
            media.setTravelStory(travelStorySave);
            mediaList.add(media);
        }
        travelStorySave.setMedia(mediaList);
        TravelStory travelStoryResult = travelStoryRepository.saveAndFlush(travelStorySave);
        travelStoryDTO.setId(travelStorySave.getId());
        return travelStoryDTO;
    }

    @Override
    public void deleteTravelStory(long id) {
        TravelStory travelStory = travelStoryRepository.findTravelStoryById(id);
        travelStory.setTravelStoryStatus(TravelStoryStatus.STATUS_INACTIVE);
        travelStoryRepository.saveAndFlush(travelStory);
    }

    @Override
    public TravelStory getByHead(String head) {
        return travelStoryRepository.findByHead(head);
    }

    @Override
    public TravelStoryDTO editTravelStory(TravelStoryDTO travelStoryDTO, long id) {
        TravelStory travelStoryBase = travelStoryRepository.findTravelStoryById(id);
        travelStoryDTO.setId(id);
        travelStoryBase.setHead(travelStoryDTO.getHead());
        travelStoryBase.setDescription(travelStoryDTO.getDescription());
        travelStoryBase.setUserOwner(userRepository.findUserById(travelStoryDTO.getUserId()));
        travelStoryBase.setUpdatedDate(LocalDateTime.now());
        travelStoryBase.setTravelStoryStatus(TravelStoryStatus.STATUS_ACTIVE);
        TravelStory travelStorySave = travelStoryRepository.saveAndFlush(travelStoryBase);
        List<MediaDTO> mediaDTOS = travelStoryDTO.getMedia();
        List<Media> mediaList = new ArrayList<>();
        for (MediaDTO mediaDTO : mediaDTOS) {
            Media media = modelMapper.map(mediaDTO, Media.class);
            media.setTravelStory(travelStorySave);
            mediaList.add(media);
        }
        travelStorySave.setMedia(mediaList);
        TravelStory travelStoryResult = travelStoryRepository.saveAndFlush(travelStorySave);
        travelStoryDTO.setId(travelStoryResult.getId());
        return travelStoryDTO;
    }

    @Override
    public List<TravelStory> getAll() {
        return travelStoryRepository.findAll();
    }

    @Override
    public TravelStory getById(long id) {
        return travelStoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Travel story not found", ExceptionCode.TRAVELSTORY_NOT_FOUND));
    }

    @Override
    public List<TravelStoryDTO> getByUserOwner(long id) {
        List<TravelStory> travelStories = travelStoryRepository.findByUserOwnerId(id);
        List<TravelStoryDTO> travelStoryDTOS = new ArrayList<>();
        for (TravelStory travelStory : travelStories) {
            if (travelStory.getTravelStoryStatus().toString().equals("STATUS_ACTIVE")) {
                TravelStoryDTO map = modelMapper.map(travelStory, TravelStoryDTO.class);
                travelStoryDTOS.add(map);
            }
        }
        return travelStoryDTOS;
    }

    @Override
    public void addMedia(long id) {

    }
}
