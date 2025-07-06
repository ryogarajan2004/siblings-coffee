package com.siblingscup.coffee.controller;

import com.siblingscup.coffee.model.Customer;
import com.siblingscup.coffee.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.siblingscup.coffee.dto.CustomerDTO;

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
    public Customer createCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer =new Customer();
        customer.setName(customerDTO.getName()==null?"":customerDTO.getName());
        customer.setPhone(customerDTO.getPhone()==null?"":customerDTO.getPhone());
        customer.setEmail(customerDTO.getEmail()==null?"":customerDTO.getEmail());
        customer.setLoyaltypoints(0);
        return customerService.saveCustomer(customer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}
