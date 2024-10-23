package com.blogjpa.repository;

import com.blogjpa.model.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBlogRepository extends CrudRepository<Blog, Long> {
    List<Blog> findByAuthor(String author);
    List<Blog> findByTitleContaining(String keyword);
}