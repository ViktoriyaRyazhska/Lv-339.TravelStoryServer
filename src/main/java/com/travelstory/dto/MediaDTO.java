package com.travelstory.dto;

import lombok.Data;

@Data
public class MediaDTO extends BaseDTO {
    private Long id;
    private String url;
    private MediaType mediaType;

    public enum MediaType {
        IMAGE, VIDEO
    }
}
