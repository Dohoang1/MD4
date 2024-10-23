package com.blogjpa.service;

import com.blogjpa.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBlogService {
    List<Blog> findAll();
    Blog findById(Long id);
    void save(Blog blog);
    void delete(Long id);
    List<Blog> findByAuthor(String author);
    List<Blog> findByTitleContaining(String keyword);
    Page<Blog> findAll(Pageable pageable);
}