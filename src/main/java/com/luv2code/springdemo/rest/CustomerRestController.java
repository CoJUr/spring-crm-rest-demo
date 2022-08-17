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

//        if null, just returning an empty json object... fix:
       Customer theCustomer = customerService.getCustomer(customerId);

           if (theCustomer == null) {
                throw new CustomerNotFoundException("Customer id not found: " + customerId);
            }
        return theCustomer;
    }
// mapping for POST to /customers - add new customer with @RequestBody to access the request body as a POJO - no json parsing
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer theCustomer) {
        //@Requestbody to access the request body as a POJO - json parsed by jackson
        // set id to 0 in case they send in an id with the json -- forces a save(insert) of the new item rather than update
        // delegate to service, save the customer
        theCustomer.setId(0);
        customerService.saveCustomer(theCustomer);
        return theCustomer;
    }



//    sending json to spring rest controller: need to set http request header to Content-Type: application/json
    //this way it can process json data.   (rest client needs to send correct http request header)

    //update existing customer - PUT mapping to update customer
    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer theCustomer) {
        customerService.saveCustomer(theCustomer);
        return theCustomer;
    }


    //delete existing customer - DELETE mapping to delete customer
    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {
        Customer tempCustomer = customerService.getCustomer(customerId);
        if (tempCustomer == null) {
            throw new CustomerNotFoundException("Customer id not found: " + customerId);
        }
        customerService.deleteCustomer(customerId);
        return "Deleted customer id - " + customerId;
    }


}
