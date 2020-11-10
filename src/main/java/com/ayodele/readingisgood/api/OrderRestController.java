package com.ayodele.readingisgood.api;

import com.ayodele.readingisgood.dto.OrderRequest;
import com.ayodele.readingisgood.dto.OrderResponse;
import com.ayodele.readingisgood.entities.Orders;
import com.ayodele.readingisgood.error.ApiErrorResponse;
import com.ayodele.readingisgood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    @GetMapping(path = "/customer/{customerId}", produces = "application/json")
    public ResponseEntity<List<Orders>> getOrderByCustomer(@PathVariable @Valid Integer customerId) {
        try {
            List<Orders> customerOrder = this.orderService.getOrderByCustomer(customerId);
            return new ResponseEntity<>(customerOrder, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST.toString(),ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    // View order
    @GetMapping(path = "/{orderId}", produces = "application/json")
    public ResponseEntity<Orders> getOrderById(@PathVariable @Valid Integer orderId) {
        try {
            Optional<Orders> order = this.orderService.findById(orderId);
            if(order.isPresent()){
                return new ResponseEntity(order, HttpStatus.OK);
            }else{
                return new ResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST.toString(),"Order id does not exist"), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST.toString(),ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
