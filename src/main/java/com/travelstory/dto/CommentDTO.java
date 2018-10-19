package com.travelstory.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO extends BaseDTO {
    private Long id;
    private Long userId;
    private Long travelStoryId;
    private Long mediaId;
    private String userFirstName;
    private String userLastName;
    private String userProfilePic;
    private String commentMassage;
    private LocalDateTime createdTime;
}
