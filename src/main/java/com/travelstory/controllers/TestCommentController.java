package com.travelstory.controllers;

import com.travelstory.entity.Comment;
import com.travelstory.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class TestCommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Comment> showComments() {
        return commentService.getAllComments();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Comment showComments(@RequestParam("id") Long id) {
        return commentService.getComment(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void saveComment(@RequestParam Comment comment) {
        commentService.saveComment(comment);
    }

}
