package com.travelstory.dto;

import lombok.Data;

@Data
public class UserSearchDTO extends BaseDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String profilePic;
}
