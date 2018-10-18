package com.travelstory.dto;

import com.travelstory.entity.Media;
import lombok.Data;

@Data
public class MediaDTO {
    private Long id;
    private String url;
    private Media.MediaType mediaType;

    public enum MediaType {
        IMAGE, VIDEO
    }
}
