package com.ayodele.readingisgood.repositories;

import com.ayodele.readingisgood.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

//    @Query("Write Query Here")
//    List<OrderResponse> findByCustomer(Customer customer);

}
