package com.travelstory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class LikeDTO extends BaseDTO {
    private Long id;

    private Long travelStoryId;

    private Long userId;

    private Long mediaId;

    private LocalDateTime createdAt;

}
