package com.siblingscup.coffee.service;

import com.siblingscup.coffee.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderBroadcastService {

    @Autowired
    private SimpMessagingTemplate template;

    public void broadcastKitchenOrders(List<Order> kitchenOrders) {
        template.convertAndSend("/topic/orders", kitchenOrders);
    }
}
