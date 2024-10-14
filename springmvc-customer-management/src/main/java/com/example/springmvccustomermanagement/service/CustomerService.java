package com.example.springmvccustomermanagement.service;

import com.example.springmvccustomermanagement.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private List<Customer> customers;

    public CustomerService() {
        customers = new ArrayList<>();
        customers.add(new Customer(1, "John Doe", "john@example.com", "123 Main St"));
        customers.add(new Customer(2, "Jane Smith", "jane@example.com", "456 Elm St"));
        customers.add(new Customer(3, "Bob Johnson", "bob@example.com", "789 Oak St"));
        customers.add(new Customer(4, "Alice Brown", "alice@example.com", "321 Pine St"));
        customers.add(new Customer(5, "Charlie Davis", "charlie@example.com", "654 Maple St"));
        customers.add(new Customer(6, "Eva Wilson", "eva@example.com", "987 Cedar St"));
        customers.add(new Customer(7, "Frank Miller", "frank@example.com", "147 Birch St"));
        customers.add(new Customer(8, "Grace Lee", "grace@example.com", "258 Walnut St"));
        customers.add(new Customer(9, "Henry Taylor", "henry@example.com", "369 Spruce St"));
        customers.add(new Customer(10, "Ivy Clark", "ivy@example.com", "741 Fir St"));
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public Customer getCustomerById(int id) {
        return customers.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void updateCustomer(Customer updatedCustomer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == updatedCustomer.getId()) {
                customers.set(i, updatedCustomer);
                break;
            }
        }
    }
}