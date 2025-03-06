package com.siblingscup.coffee.controller;

import com.siblingscup.coffee.model.Customer;
import com.siblingscup.coffee.model.Ingredient;
import com.siblingscup.coffee.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer>getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer>getCustomerById(@PathVariable Long id){
        Optional <Customer> customer=customerService.getCustomerById(id);

        return customer.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}
