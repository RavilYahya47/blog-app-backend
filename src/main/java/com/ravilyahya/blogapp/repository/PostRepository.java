package com.ravilyahya.blogapp.repository;

import com.ravilyahya.blogapp.model.Category;
import com.ravilyahya.blogapp.model.Post;
import com.ravilyahya.blogapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

}
