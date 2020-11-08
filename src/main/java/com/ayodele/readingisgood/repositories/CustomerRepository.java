package com.ayodele.readingisgood.repositories;

import com.ayodele.readingisgood.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
