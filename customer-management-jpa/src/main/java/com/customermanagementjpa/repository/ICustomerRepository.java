package com.customermanagementjpa.repository;

import com.customermanagementjpa.model.Customer;
import org.springframework.stereotype.Service;

@Service
public interface ICustomerRepository extends IGenerateRepository<Customer>   {
}
