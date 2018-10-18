package com.travelstory.dto;

import java.time.LocalDateTime;

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
