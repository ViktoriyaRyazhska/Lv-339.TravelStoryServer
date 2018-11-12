package com.travelstory.services;

import com.travelstory.dto.MediaDTO;
import com.travelstory.dto.TravelStoryDTO;
import com.travelstory.dto.converter.MediaConverter;
import com.travelstory.dto.converter.TravelStoryConverter;
import com.travelstory.entity.TravelStory;
import com.travelstory.entity.TravelStoryStatus;
import com.travelstory.exceptions.ResourceNotFoundException;
import com.travelstory.exceptions.codes.ExceptionCode;
import com.travelstory.repositories.TravelStoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelStoryServiceImpl implements TravelStoryService {
    @Autowired
    private TravelStoryRepository travelStoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TravelStoryConverter travelStoryConverter;
    @Autowired
    private MediaConverter mediaConverter;

    @Override
    public TravelStoryDTO addTravelStory(TravelStoryDTO travelStoryDTO) {
        TravelStory travelStory = travelStoryConverter.convertToEntity(travelStoryDTO);
        TravelStory travelStorySave = travelStoryRepository.saveAndFlush(travelStory);

        List<MediaDTO> mediaDTOS = travelStoryDTO.getMedia();
        travelStorySave.setMedia(mediaConverter.convertToEntity(mediaDTOS, travelStorySave));
        TravelStory travelStoryResult = travelStoryRepository.saveAndFlush(travelStorySave);
        travelStoryDTO.setId(travelStoryResult.getId());
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
        travelStoryBase = travelStoryConverter.convertToEntity(travelStoryDTO, travelStoryBase);
        TravelStory travelStorySave = travelStoryRepository.saveAndFlush(travelStoryBase);
        List<MediaDTO> mediaDTOS = travelStoryDTO.getMedia();
        travelStorySave.setMedia(mediaConverter.convertToEntity(mediaDTOS, travelStorySave));
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
        List<TravelStory> travelStories = travelStoryRepository.findAllByUserOwnerIdAndTravelStoryStatus(id,
                TravelStoryStatus.STATUS_ACTIVE);
        return travelStoryConverter.convertToDto(travelStories);
    }
}
