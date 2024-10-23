package com.customermanagementjpa.service;

import com.customermanagementjpa.model.Customer;
import com.customermanagementjpa.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService{

   @Autowired
    private ICustomerRepository ICustomerRepository;

    @Override
    public List<Customer> findAll() {
        return ICustomerRepository.findAll();
    }

    @Override
    public void save (Customer customer) {
        ICustomerRepository.save(customer);
    }

    public Customer findById (Long id) {
        return ICustomerRepository.findById(id);
    }

    public void remove (Long id) {
        ICustomerRepository.remove(id);
    }
}
