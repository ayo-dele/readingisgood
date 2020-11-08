package com.ayodele.readingisgood.service;

import com.ayodele.readingisgood.entities.Customer;
import org.springframework.dao.DataAccessException;

import java.util.Optional;

public interface CustomerService {
    void saveCustomer(Customer customer) throws DataAccessException;

    Optional<Customer> findById(Integer customerID);
}
