package com.luv2code.springdemo.rest;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {


    //autowire customerservice
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {

//        if null, returning back an empty json object...
       Customer theCustomer = customerService.getCustomer(customerId);

           if (theCustomer == null) {
                throw new CustomerNotFoundException("Customer id not found: " + customerId);
            }
        return theCustomer;
    }


}
