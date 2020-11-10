package com.ayodele.readingisgood.service;

import com.ayodele.readingisgood.dto.OrderRequest;
import com.ayodele.readingisgood.dto.OrderResponse;
import com.ayodele.readingisgood.entities.Orders;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderResponse saveOrder(OrderRequest orderRequest) throws Exception;
    List<Orders> getOrderByCustomer(Integer customerId) throws Exception;
    Optional<Orders> findById(Integer orderId);
}
