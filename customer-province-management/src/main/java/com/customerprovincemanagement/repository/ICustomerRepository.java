package com.customerprovincemanagement.repository;

import com.customerprovincemanagement.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {
}