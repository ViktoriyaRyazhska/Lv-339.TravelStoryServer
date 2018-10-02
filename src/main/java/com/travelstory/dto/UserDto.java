package com.travelstory.dto;

import lombok.Data;

@Data
public class UserDto {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    Long id;
    String profilePic;

}
