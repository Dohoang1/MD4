package com.blogjpa.controller;

import com.blogjpa.service.AdminService;
import com.blogjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(required = false) String redirectUrl, Model model) {
        model.addAttribute("redirectUrl", redirectUrl);
        return "access/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam(required = false) String redirectUrl,
                        HttpSession session,
                        Model model) {
        if (adminService.authenticate(username, password)) {
            session.setAttribute("admin", username);
            System.out.println("Admin logged in: " + username);
            return redirectUrl != null && !redirectUrl.isEmpty() ? "redirect:" + redirectUrl : "redirect:/blogs";
        } else if (userService.authenticate(username, password)) {
            session.setAttribute("user", username);
            System.out.println("User logged in: " + username);
            return "redirect:/blogs";
        } else {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
            return "access/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("admin");
        session.removeAttribute("user");
        return "redirect:/login";
    }
}