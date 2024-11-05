package com.codegym.controller;


import com.codegym.model.Product;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @GetMapping("/products")
    public ModelAndView listProduct() {
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", iProductService.findAll());
        return modelAndView;
    }

    @GetMapping("/products/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/products/create")
    public ModelAndView saveProduct(@ModelAttribute ("product") Product product) {
        iProductService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("message", "Product created successfully!");
        return modelAndView;
    }

    @GetMapping("/products/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id) {
        Optional<Product> product = iProductService.findById(id);
        if(product.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/product/update");
            modelAndView.addObject("product", product.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/products/update")
    public ModelAndView updateProduct(@ModelAttribute ("product") Product product) {
        iProductService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/update");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "Product updated successfully!");
        return modelAndView;
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        iProductService.remove(id);
        return "redirect:/products";
    }
}


