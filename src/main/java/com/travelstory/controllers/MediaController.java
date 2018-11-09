package com.travelstory.controllers;

import com.travelstory.dto.MediaDTO;
import com.travelstory.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class MediaController {
    @Autowired
    MediaService mediaService;

    @GetMapping("/media/{userId}/{page}/{size}")
    public Page<MediaDTO> getFollowing(@PathVariable(value = "userId") Long userId,
            @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
        return mediaService.getUserMedias(userId, page, size);
    }
}
