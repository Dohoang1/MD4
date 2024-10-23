package com.blogjpa.model;

import org.springframework.web.multipart.MultipartFile;

public class BlogUploadForm {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String uploadTime;
    private MultipartFile image;

    public BlogUploadForm() {
    }

    public BlogUploadForm(Long id, String title, String content, String author, String uploadTime, MultipartFile image) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.uploadTime = uploadTime;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}