package com.travelstory.dto;

import com.travelstory.entity.MediaType;
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

    private Long userId;

    private Long contentId;

    private MediaType mediaType;

    private LocalDateTime createdAt;

}
