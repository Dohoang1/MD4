package com.productmanagement.controller;

import com.productmanagement.model.Product;
import com.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product/list";
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/add";
    }
    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes) {
        productService.addProduct(product);
        redirectAttributes.addFlashAttribute("message", "Sản phẩm mới đã được thêm thành công.");
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "product/edit";
    }
    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        productService.updateProduct(product);
        redirectAttributes.addFlashAttribute("message", "Sản phẩm đã được cập nhật thành công.");
        return "redirect:/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id, RedirectAttributes redirectAttributes) {
        productService.deleteProduct(id);
        redirectAttributes.addFlashAttribute("message", "Sản phẩm đã được xóa thành công.");
        return "redirect:/products";
    }

    @GetMapping("/details/{id}")
    public String showProductDetails(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "product/details";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam(required = false) String name, Model model) {
        List<Product> searchResults;
        if (name != null && !name.isEmpty()) {
            searchResults = productService.searchByName(name);
        } else {
            searchResults = productService.getAllProducts();
        }
        model.addAttribute("products", searchResults);
        model.addAttribute("searchName", name);
        return "product/list";
    }
}