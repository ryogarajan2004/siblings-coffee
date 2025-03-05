package com.siblingscup.coffee.repository;

import com.siblingscup.coffee.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepoository  extends JpaRepository<OrderItem,Long> {
}
