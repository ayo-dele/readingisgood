package com.ayodele.readingisgood.service;

import com.ayodele.readingisgood.dto.OrderRequest;
import com.ayodele.readingisgood.dto.OrderResponse;

public interface OrderService {
    OrderResponse saveOrder(OrderRequest orderRequest) throws Exception;
}
