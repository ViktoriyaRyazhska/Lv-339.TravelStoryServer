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
    public List<CommentDTO> getComments(@RequestParam(value = "contentId") Long contentId,
            @RequestParam(value = "contentType") String contentType) {
        return commentService.getAllComments(contentId, contentType);
    }

    @PostMapping("comments")
    public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO dto) {
        CommentDTO addedComment = commentService.add(dto);
        return new ResponseEntity<>(addedComment, HttpStatus.CREATED);
    }

    @GetMapping("comments/{contentType}")
    public Page<CommentDTO> getFirstComments(@RequestParam(value = "contentId") Long contentId,
            @PathVariable(value = "contentType") String contentType,
            @RequestParam(value = "pageNumber") int pageNumber) {
        return commentService.getNext3Comments(contentId, contentType, pageNumber);
    }

    @DeleteMapping("comments/{id}")
    public ResponseEntity<CommentDTO> deleteLike(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
