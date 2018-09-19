package com.travelstory.controllers;

import com.travelstory.entity.Comment;
import com.travelstory.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class TestCommentController {

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
    public void saveComment(@RequestBody Comment comment) {
        commentService.saveComment(comment);
    }

}
