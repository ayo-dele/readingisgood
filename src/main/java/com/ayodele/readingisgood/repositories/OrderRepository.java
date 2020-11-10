package com.ayodele.readingisgood.repositories;

import com.ayodele.readingisgood.entities.Customer;
import com.ayodele.readingisgood.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findByCustomer(Customer customer);
}
