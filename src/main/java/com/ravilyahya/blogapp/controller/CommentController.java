package com.ravilyahya.blogapp.controller;

import com.ravilyahya.blogapp.payloads.ApiResponse;
import com.ravilyahya.blogapp.payloads.CommentDTO;
import com.ravilyahya.blogapp.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable Long postId){
        CommentDTO savedComment = commentService.createComment(commentDTO, postId);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }


    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment succesfullt deleted!",true), HttpStatus.OK);
    }


}
