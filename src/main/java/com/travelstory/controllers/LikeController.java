package com.travelstory.controllers;

import com.travelstory.dto.LikeDTO;
import com.travelstory.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/")
public class LikeController {

    @Autowired()
    private LikeService likeService;

    @GetMapping("likes")
    public List<LikeDTO> getLikes(@RequestParam(value = "travelStoryId") Long travelStoryId,
                                  @RequestParam(value = "mediaId", required = false) Long mediaId) {
        return likeService.getLikes(travelStoryId, mediaId);
    }

    @PostMapping("likes")
    public ResponseEntity<LikeDTO> addLike(@RequestBody LikeDTO likeDTO) {
        LikeDTO addedLike = likeService.add(likeDTO);
        return new ResponseEntity<>(addedLike, HttpStatus.CREATED);
    }

    @DeleteMapping("likes/{id}")
    public ResponseEntity<LikeDTO> deleteLike(@PathVariable Long id) {
        likeService.deleteLike(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
