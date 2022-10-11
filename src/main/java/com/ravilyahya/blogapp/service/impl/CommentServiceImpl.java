package com.ravilyahya.blogapp.service.impl;

import com.ravilyahya.blogapp.exception.ResourceNotFoundException;
import com.ravilyahya.blogapp.model.Comment;
import com.ravilyahya.blogapp.model.Post;
import com.ravilyahya.blogapp.payloads.CommentDTO;
import com.ravilyahya.blogapp.repository.CommentRepository;
import com.ravilyahya.blogapp.repository.PostRepository;
import com.ravilyahya.blogapp.service.CommentService;
import com.ravilyahya.blogapp.util.DTOConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final DTOConverter dtoConverter;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
        Comment comment = dtoConverter.commentDTOToComment(commentDTO);

        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);

        return dtoConverter.commentToCommentDTO(savedComment);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment","comment id",commentId));
        commentRepository.delete(comment);

    }
}
