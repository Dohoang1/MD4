package com.blogjpa.service;

import com.blogjpa.model.Blog;
import com.blogjpa.repository.IBlogRepository;
import com.blogjpa.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService implements IBlogService {

    @Autowired
    private IBlogRepository blogRepository;

    @Autowired
    private FileUploadUtil fileUploadUtil;


    @Override
    public List<Blog> findAll() {
        return (List<Blog>) blogRepository.findAll();
    }

    @Override
    public Blog findById(Long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        return blog.orElse(null);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void delete(Long id) {
        Blog blog = findById(id);
        if (blog != null && blog.getImage() != null) {
            try {
                fileUploadUtil.deleteFile(blog.getImage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        blogRepository.deleteById(id);
    }

    @Override
    public List<Blog> findByAuthor(String author) {
        return blogRepository.findByAuthor(author);
    }

    @Override
    public List<Blog> findByTitleContaining(String keyword) {
        return blogRepository.findByTitleContaining(keyword);
    }

    public Blog saveBlogWithImage(Blog blog, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = fileUploadUtil.saveFile(imageFile);
            blog.setImage(fileName);
        }
        return blogRepository.save(blog);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }
}
