package com.travelstory.dto;

import lombok.Data;
import java.util.List;

@Data
public class TravelStoryDTO {

    private long id;
    private String head;
    private String description;
    private long userId;

    private List<MediaDTO> media;
}
