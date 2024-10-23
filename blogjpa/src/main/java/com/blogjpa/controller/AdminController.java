package com.blogjpa.controller;

import com.blogjpa.service.AdminService;
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

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(required = false) String redirectUrl, Model model) {
        model.addAttribute("redirectUrl", redirectUrl);
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam(required = false) String redirectUrl,
                        HttpSession session,
                        Model model) {
        if (adminService.authenticate(username, password)) {
            session.setAttribute("admin", username);
            return redirectUrl != null && !redirectUrl.isEmpty() ? "redirect:" + redirectUrl : "redirect:/blogs";
        } else {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
            return "admin/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("admin");
        return "redirect:/login";
    }
}