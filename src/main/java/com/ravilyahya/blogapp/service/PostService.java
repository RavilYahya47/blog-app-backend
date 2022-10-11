package com.ravilyahya.blogapp.service;

import com.ravilyahya.blogapp.payloads.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO, Long userId,Long categoryId);
    PostDTO updatePost(PostDTO postDTO, Long postId);
    void deletePost(Long postId);
    List<PostDTO> getAllPosts();
    PostDTO getPostById(Long postId);
    List<PostDTO> getPostsByCategory(Long categoryId);
    List<PostDTO> getPostsByUser(Long userId);
    List<PostDTO> searchPosts(String keyword);

}
