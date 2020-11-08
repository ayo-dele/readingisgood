package com.ayodele.readingisgood.service;

import com.ayodele.readingisgood.entities.Customer;
import com.ayodele.readingisgood.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void saveCustomer(Customer customer) throws DataAccessException {
        customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Integer customerID) {
        return customerRepository.findById(customerID);
    }
}
