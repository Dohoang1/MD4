package com.sandwichcondiments.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CondimentsController {
    @GetMapping("/condiments")
    public String showCondiments() {
        return "condiments";
    }

    @PostMapping("/save")
    public String save(@RequestParam("condiment") String[] condiments, Model model) {
        model.addAttribute("selectedCondiments", condiments);
        return "result";
    }
}
