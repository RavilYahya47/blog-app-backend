package com.ravilyahya.blogapp.payloads;

import com.ravilyahya.blogapp.model.Category;
import com.ravilyahya.blogapp.model.Comment;
import com.ravilyahya.blogapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long postId;
    private String title;
    private String content;
    private String imageName;
    private CategoryDTO category;
    private UserDTO user;
    private Set<CommentDTO> comments = new HashSet<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
