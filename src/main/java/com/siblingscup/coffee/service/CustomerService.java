package com.siblingscup.coffee.service;


import com.siblingscup.coffee.model.Customer;
import com.siblingscup.coffee.model.Ingredient;
import com.siblingscup.coffee.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private CustomerRepository repository;

    public List<Customer>getAllCustomers(){
        return repository.findAll();
    }

    public Optional<Customer>getCustomerById(Long id){
        return repository.findById(id);
    }

    public Customer saveCustomer(Customer customer){
        return repository.save(customer);
    }


    public void deleteCustomer(Long id){
        repository.deleteById(id);
    }
}
