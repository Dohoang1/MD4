package com.blogjpa.repository;

import com.blogjpa.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByAuthor(String author);
    List<Blog> findByTitleContaining(String keyword);
    Page<Blog> findAll(Pageable pageable);
}