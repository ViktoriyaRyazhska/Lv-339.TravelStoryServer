package com.travelstory.controllers;

import com.travelstory.dto.MediaDTO;
import com.travelstory.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("media/{id}")
    public ResponseEntity<MediaDTO> deleteMedia(@PathVariable Long id) {
        mediaService.deleteMedia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
