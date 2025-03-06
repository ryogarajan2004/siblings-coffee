package com.siblingscup.coffee.controller;


import com.siblingscup.coffee.model.Order;
import com.siblingscup.coffee.model.OrderItem;
import com.siblingscup.coffee.service.OrderItemService;
import com.siblingscup.coffee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orderItem")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public List<OrderItem>getAllOrderItems(){
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id){
        Optional<OrderItem> orderItem=orderItemService.getOrderItemById(id);
        return orderItem.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem){
        return orderItemService.saveOrderItem(orderItem);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrderItem(@PathVariable Long id){
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.ok().build();
    }
}
