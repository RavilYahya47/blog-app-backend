package com.ravilyahya.blogapp.service;

import com.ravilyahya.blogapp.payloads.CommentDTO;

public interface CommentService {

    CommentDTO createComment(CommentDTO commentDTO,Long postId);
    void deleteComment(Long commentId);
}
