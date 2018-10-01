package com.travelstory.controllers;

import com.travelstory.dto.LikeDTO;
import com.travelstory.entity.Like;
import com.travelstory.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @GetMapping
    public List<LikeDTO> getLikes(@RequestParam(value = "travelStoryId") Long travelStoryId,
                                  @RequestParam(value = "mediaId", required = false)Long mediaId) {
        return likeService.getLikes(travelStoryId,mediaId);
    }

//    @PostMapping
//    public ResponseEntity<Like> addLike(@RequestBody Like like, @RequestParam(value = "userId") Long userId,
//                                        @RequestParam(value = "travelStoryId") Long travelStoryId,
//                                        @RequestParam(value = "mediaId", required = false) Long mediaId) {
//        Like addedLike = likeService.add(like, userId, travelStoryId, mediaId);
//        return new ResponseEntity<>(addedLike, HttpStatus.CREATED);
//    }



}
