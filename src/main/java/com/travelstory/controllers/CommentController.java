package com.travelstory.controllers;

import com.travelstory.dto.CommentDTO;
import com.travelstory.entity.Comment;
import com.travelstory.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("comments")
    public List<CommentDTO> getComments(@RequestParam(value = "travelStoryId") Long travelStoryId,
                                     @RequestParam(value = "mediaId", required = false) Long mediaId) {
        return commentService.getAllComments(travelStoryId,mediaId);
    }

    @GetMapping("{id}")
    public Comment showComments(@RequestParam("id") Long id) {
        return commentService.getComment(id);
    }

    @PostMapping("comments")
    public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO commentDTO) {
        CommentDTO addedComment = commentService.add(commentDTO);
        return new ResponseEntity<>(addedComment, HttpStatus.CREATED);
    }

}
