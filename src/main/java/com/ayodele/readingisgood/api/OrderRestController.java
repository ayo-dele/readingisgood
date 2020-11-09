package com.ayodele.readingisgood.api;

import com.ayodele.readingisgood.dto.OrderRequest;
import com.ayodele.readingisgood.dto.OrderResponse;
import com.ayodele.readingisgood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/orders")
public class OrderRestController {

    private final OrderService orderService;

    @Autowired
    private OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    //Place a new order and update stock
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<OrderResponse> addNewOrder(@RequestBody @Valid OrderRequest orderRequest, BindingResult bindingResult) {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || (orderRequest == null)) {
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        try {
            OrderResponse orderResponse = this.orderService.saveOrder(orderRequest);
            return new ResponseEntity<>(orderResponse, headers, HttpStatus.CREATED);
        } catch (Exception ex) {
            headers.add("errors", ex.getMessage());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    //List all orders for a customer

//    @GetMapping(path = "/customer", produces = "application/json", consumes = "application/json")
//    public ResponseEntity<OrderResponse> getOrderByCustomer(@RequestBody @Valid Integer customerId, BindingResult bindingResult) {
//        BindingErrorsResponse errors = new BindingErrorsResponse();
//        HttpHeaders headers = new HttpHeaders();
//        if (bindingResult.hasErrors() || (customerId == null)) {
//            errors.addAllErrors(bindingResult);
//            headers.add("errors", errors.toJSON());
//            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
//        }
//        try {
//            OrderResponse orderResponse = this.orderService.saveOrder(orderRequest);
//            return new ResponseEntity<>(orderResponse, headers, HttpStatus.CREATED);
//        } catch (Exception ex) {
//            headers.add("errors", ex.getMessage());
//            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
//        }
//    }

    // View order

}
