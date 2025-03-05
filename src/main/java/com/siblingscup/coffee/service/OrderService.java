package com.siblingscup.coffee.service;

import com.siblingscup.coffee.model.Order;
import com.siblingscup.coffee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private OrderRepository repository;

    public List<Order>getAllOrders(){
        return  repository.findAll();
    }

    public Optional<Order>getOrderById(Long id){
        return repository.findById(id);
    }

    public Order saveOrder(Order order){
        return repository.save(order);
    }

    public void deleteOrder(Long id){
        repository.deleteById(id);
    }

}
