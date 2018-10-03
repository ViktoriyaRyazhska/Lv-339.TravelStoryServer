package com.travelstory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class LikeDTO extends BaseDTO {
    private Long id;
    private boolean likeState;
    private Long travelStoryId;
    private Long userId;
    private Long mediaId;


    public boolean isLikeState() {
        return likeState;
    }
}
