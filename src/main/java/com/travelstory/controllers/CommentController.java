package com.travelstory.controllers;

import com.travelstory.dto.CommentDTO;
import com.travelstory.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        return commentService.getAllComments(travelStoryId, mediaId);
    }

    @PostMapping("comments")
    public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO commentDTO) {
        CommentDTO addedComment = commentService.add(commentDTO);
        return new ResponseEntity<>(addedComment, HttpStatus.CREATED);
    }

    @GetMapping("comments/{travelStoryId}")
    public Page<CommentDTO> getFirstComments(@PathVariable(value = "travelStoryId") Long travelStoryId,
            @RequestParam(value = "mediaId", required = false) Long mediaId,
            @RequestParam(value = "pageNumber") int pageNumber) {
        return commentService.getNext3Comments(travelStoryId, mediaId, pageNumber);
    }

    @DeleteMapping("comments/{id}")
    public ResponseEntity<CommentDTO> deleteLike(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
