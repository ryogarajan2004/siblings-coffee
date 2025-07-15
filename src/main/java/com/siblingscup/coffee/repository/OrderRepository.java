package com.siblingscup.coffee.repository;

import com.siblingscup.coffee.model.Order;
import com.siblingscup.coffee.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository <Order,Long> {

    List<Order> findByStatusNot(OrderStatus status);
}
