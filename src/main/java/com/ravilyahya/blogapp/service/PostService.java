package com.ravilyahya.blogapp.service;

import com.ravilyahya.blogapp.payloads.PostDTO;
import com.ravilyahya.blogapp.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO, Long userId,Long categoryId);
    PostDTO updatePost(PostDTO postDTO, Long postId);
    void deletePost(Long postId);

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    PostDTO getPostById(Long postId);
    List<PostDTO> getPostsByCategory(Long categoryId);
    List<PostDTO> getPostsByUser(Long userId);
    List<PostDTO> searchPosts(String keyword);

}
