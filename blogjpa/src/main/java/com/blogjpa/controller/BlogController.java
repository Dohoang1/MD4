package com.blogjpa.controller;

import com.blogjpa.model.Blog;
import com.blogjpa.model.BlogUploadForm;
import com.blogjpa.service.IBlogService;
import com.blogjpa.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @GetMapping
    public String listBlogs(Model model,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "5") int size,
                            HttpSession session) {
        Page<Blog> blogPage = blogService.findAll(PageRequest.of(page, size));
        model.addAttribute("blogs", blogPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", blogPage.getTotalPages());
        model.addAttribute("totalItems", blogPage.getTotalElements());

        if (session.getAttribute("admin") != null) {
            model.addAttribute("loggedInUser", session.getAttribute("admin"));
            model.addAttribute("isAdmin", true);
        } else if (session.getAttribute("user") != null) {
            model.addAttribute("loggedInUser", session.getAttribute("user"));
            model.addAttribute("isAdmin", false);
        }

        return "blog/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }
        model.addAttribute("blogForm", new BlogUploadForm());
        return "admin/create";
    }

    @PostMapping("/create")
    public String createBlog(@ModelAttribute("blogForm") @Valid BlogUploadForm blogForm,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }
        if (bindingResult.hasErrors()) {
            return "admin/create";
        }

        try {
            Blog blog = convertToBlog(blogForm);
            blog.setUploadTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            if (blogForm.getImage() != null && !blogForm.getImage().isEmpty()) {
                String fileName = fileUploadUtil.saveFile(blogForm.getImage());
                blog.setImage(fileName);
            }

            blogService.save(blog);
            redirectAttributes.addFlashAttribute("message", "Blog created successfully!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Failed to upload image.");
            return "redirect:/blogs/create";
        }

        return "redirect:/blogs";
    }

    @GetMapping("/{id}")
    public String viewBlog(@PathVariable Long id, Model model) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return "error/404";
        }
        model.addAttribute("blog", blog);
        return "blog/view";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return "error/404";
        }
        BlogUploadForm blogForm = convertToBlogForm(blog);
        model.addAttribute("blogForm", blogForm);
        return "admin/edit";
    }

    @PostMapping("/edit")
    public String editBlog(@ModelAttribute("blogForm") @Valid BlogUploadForm blogForm,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }
        if (bindingResult.hasErrors()) {
            return "admin/edit";
        }

        try {
            Blog blog = blogService.findById(blogForm.getId());
            if (blog == null) {
                return "error/404";
            }

            updateBlogFromForm(blog, blogForm);

            if (blogForm.getImage() != null && !blogForm.getImage().isEmpty()) {
                String fileName = fileUploadUtil.saveFile(blogForm.getImage());
                if (blog.getImage() != null) {
                    fileUploadUtil.deleteFile(blog.getImage());
                }
                blog.setImage(fileName);
            }

            blogService.save(blog);
            redirectAttributes.addFlashAttribute("message", "Blog updated successfully!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Failed to upload image.");
            return "redirect:/blogs/edit/" + blogForm.getId();
        }

        return "redirect:/blogs";
    }

    @PostMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes redirectAttributes, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return "error/404";
        }

        try {
            if (blog.getImage() != null) {
                fileUploadUtil.deleteFile(blog.getImage());
            }
            blogService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Blog deleted successfully!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete blog image.");
        }

        return "redirect:/blogs";
    }

    private Blog convertToBlog(BlogUploadForm blogForm) {
        Blog blog = new Blog();
        blog.setId(blogForm.getId());
        blog.setTitle(blogForm.getTitle());
        blog.setContent(blogForm.getContent());
        blog.setAuthor(blogForm.getAuthor());
        return blog;
    }

    private BlogUploadForm convertToBlogForm(Blog blog) {
        BlogUploadForm form = new BlogUploadForm();
        form.setId(blog.getId());
        form.setTitle(blog.getTitle());
        form.setContent(blog.getContent());
        form.setAuthor(blog.getAuthor());
        return form;
    }

    private void updateBlogFromForm(Blog blog, BlogUploadForm form) {
        blog.setTitle(form.getTitle());
        blog.setContent(form.getContent());
        blog.setAuthor(form.getAuthor());
    }
}