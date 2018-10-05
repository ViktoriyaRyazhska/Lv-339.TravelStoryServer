package com.travelstory.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO extends BaseDTO {

    private String userName;
    private String massage;
    private LocalDateTime localDateTime;
    private String profilePic;

}
