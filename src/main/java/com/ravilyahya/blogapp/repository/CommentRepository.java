package com.ravilyahya.blogapp.repository;

import com.ravilyahya.blogapp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
