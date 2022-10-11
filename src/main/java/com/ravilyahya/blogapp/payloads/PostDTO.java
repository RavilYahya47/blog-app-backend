package com.ravilyahya.blogapp.payloads;

import com.ravilyahya.blogapp.model.Category;
import com.ravilyahya.blogapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
