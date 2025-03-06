package com.siblingscup.coffee.service;


import com.siblingscup.coffee.model.OrderItem;
import com.siblingscup.coffee.repository.OrderItemRepoository;
import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private OrderItemRepoository repository;

    public List<OrderItem> getAllOrderItems(){
        return repository.findAll();
    }

    public Optional<OrderItem>getOrderItemById(Long id){
        return repository.findById(id);
    }

    public OrderItem saveOrderItem(OrderItem item){
        return repository.save(item);
    }

    public void deleteOrderItem(Long id){
        repository.deleteById(id);
    }
}
