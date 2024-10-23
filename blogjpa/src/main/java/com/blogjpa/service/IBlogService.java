package com.blogjpa.service;

import com.blogjpa.model.Blog;
import java.util.List;

public interface IBlogService {
    List<Blog> findAll();
    Blog findById(Long id);
    void save(Blog blog);
    void delete(Long id);
    List<Blog> findByAuthor(String author);
    List<Blog> findByTitleContaining(String keyword);
}