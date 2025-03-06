package com.siblingscup.coffee.controller;


import com.siblingscup.coffee.model.Order;
import com.siblingscup.coffee.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.RepositoryConfigurationSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        Optional<Order> order=orderService.getOrderById(id);

        return order.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order){
        return orderService.saveOrder(order);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity  deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);

        return ResponseEntity.ok().build();
    }

}
