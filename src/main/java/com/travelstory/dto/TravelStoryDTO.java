package com.travelstory.dto;

import lombok.Data;
import java.util.List;

@Data
public class TravelStoryDTO extends BaseDTO {

    private long id;
    private String head;
    private String description;
    private long userId;

    private List<MediaDTO> media;
}
