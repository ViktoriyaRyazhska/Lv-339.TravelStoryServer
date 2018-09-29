package com.travelstory.controllers;

import com.travelstory.entity.Like;
import com.travelstory.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class TestLikeController {
    @Autowired
    LikeService likeService;

    @GetMapping
    public List<Like> showComments() {
        return likeService.getAllLikes();
    }

    @PostMapping
    public void saveLike(@RequestBody Like like) {
        likeService.addLike(like);
    }

}
