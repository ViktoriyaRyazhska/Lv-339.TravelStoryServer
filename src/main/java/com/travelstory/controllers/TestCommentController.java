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
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment,
                                              @RequestParam(value = "transitId") Integer transitId, @RequestParam(value = "userId") Integer userId,
                                              @RequestParam(value = "parentId", required = false) Integer parentId) {
        Comment addedComment = commentService.add(comment, transitId, userId, parentId);
        return new ResponseEntity<>(modelMapper.map(addedComment, Comment.class), HttpStatus.CREATED);
    }

}
