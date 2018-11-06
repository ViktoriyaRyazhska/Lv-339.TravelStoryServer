package com.travelstory.dto;

import com.travelstory.entity.MediaType;
import lombok.Data;

import javax.mail.internet.ContentType;
import java.time.LocalDateTime;

@Data
public class CommentDTO extends BaseDTO {
    private Long id;
    private Long userId;
    private Long contentId;
    private MediaType mediaType;
    private String userFirstName;
    private String userLastName;
    private String userProfilePic;
    private String commentMassage;
    private LocalDateTime createdTime;

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }
}

