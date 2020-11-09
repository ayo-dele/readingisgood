package com.ayodele.readingisgood.api;

import com.ayodele.readingisgood.entities.Customer;
import com.ayodele.readingisgood.service.CustomerService;
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
@RequestMapping("api/customers")
public class CustomerRestController {

    private final CustomerService customerService;

    @Autowired
    private CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Register new customer

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Customer> addNewCustomer(@RequestBody @Valid Customer customer, BindingResult bindingResult) {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || (customer == null)) {
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        this.customerService.saveCustomer(customer);
        return new ResponseEntity<>(customer, headers, HttpStatus.CREATED);
    }


}
