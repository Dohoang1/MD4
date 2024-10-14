package com.example.springmvccustomermanagement.controller;

import com.example.springmvccustomermanagement.model.Customer;
import com.example.springmvccustomermanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public String showList(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "list";
    }

    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable int id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "details";
    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute Customer customer) {
        customerService.updateCustomer(customer);
        return "redirect:/customers";
    }
}
