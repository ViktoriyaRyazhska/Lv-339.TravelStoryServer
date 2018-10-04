package com.travelstory.controllers;

import com.travelstory.entity.Comment;
import com.travelstory.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Comment> showComments() {
        return commentService.getAllComments();
    }

    @GetMapping("{id}")
    public Comment showComments(@RequestParam("id") Long id) {
        return commentService.getComment(id);
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment, @RequestParam(value = "userId") Long userId,
            @RequestParam(value = "travelStoryId") Long travelStoryId,
            @RequestParam(value = "mediaId", required = false) Long mediaId) {
        Comment addedComment = commentService.add(comment, userId, travelStoryId, mediaId);
        return new ResponseEntity<>(addedComment, HttpStatus.CREATED);
    }

}
