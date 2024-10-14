package com.codegym.c0624g1customermanagement;

import com.codegym.c0624g1customermanagement.model.Customer;
import com.codegym.c0624g1customermanagement.service.CustomerServiceImpl;
import com.codegym.c0624g1customermanagement.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerServiceImpl;

    /*private CustomerServiceImpl customerServiceImpl;

    public CustomerController(CustomerServiceImpl customerServiceImpl){
        this.customerServiceImpl = customerServiceImpl;
    }*/

    /*private CustomerServiceImpl customerServiceImpl;

    @Autowired
    public void setCustomerServiceImpl(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
    }*/

    //private ICustomerService customerService = new CustomerServiceImpl();
    @GetMapping("/list")
    public String showList(HttpServletRequest request) {
        List<Customer> customers = customerServiceImpl.findAll();
        request.setAttribute("customers", customers);
        return "customers/list";
    }
}
