package com.siblingscup.coffee.service;

import com.siblingscup.coffee.model.Customer;
import com.siblingscup.coffee.model.Order;
import com.siblingscup.coffee.model.OrderItem;
import com.siblingscup.coffee.model.OrderStatus;
import com.siblingscup.coffee.model.PaymentType;
import com.siblingscup.coffee.model.Product;
import com.siblingscup.coffee.model.Sale;
import com.siblingscup.coffee.model.Transaction;
import com.siblingscup.coffee.repository.CustomerRepository;
import com.siblingscup.coffee.repository.OrderRepository;
import com.siblingscup.coffee.repository.ProductRepository;
import com.siblingscup.coffee.repository.SalesRepository;
import com.siblingscup.coffee.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.siblingscup.coffee.dto.OrderItemDTO;
import com.siblingscup.coffee.dto.OrderRequestDTO;

@Service
@RequiredArgsConstructor
public class OrderService {

    private OrderRepository repository;
    private CustomerRepository customerRepo;
    private ProductRepository productRepo;
    private OrderRepository orderRepo;
    private SalesRepository saleRepo;
    private TransactionRepository transactionRepo;
    public List<Order>getAllOrders(){
        return  repository.findAll();
    }

    public Optional<Order>getOrderById(Long id){
        return repository.findById(id);
    }

    public Order saveOrder(Order order){
        return repository.save(order);
    }

    public Order createOrder(OrderRequestDTO request){

        Customer customer=null;
       if (request.getCustomerId() != null) {
            customer = customerRepo.findById(request.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
        }
        Order order=new Order();
        order.setCustomer(customer);
        order.setOrderTime(LocalDateTime.now());
        order.setStatus(OrderStatus.NEW);


        List<OrderItem>items=new ArrayList<>();
        double total=0;

        for(OrderItemDTO itemDTO:request.getItems()){
            Product product=productRepo.findById(itemDTO.getProductId()).orElseThrow();
            OrderItem item=new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setPrice(product.getPrice()*itemDTO.getQuantity());
            total+=item.getPrice();
            items.add(item);
        }
        order.setItems(items);
        order.setTotalAmount(total);
        orderRepo.save(order);

        Sale sale=new Sale();
        sale.setOrder(order);
        sale.setSaleTime(LocalDateTime.now());
        sale.setTotalAmount(total);
        saleRepo.save(sale);

        Transaction txn=new Transaction();
        txn.setSale(sale);
        txn.setAmount(total);
        txn.setPaymentType(PaymentType.valueOf(request.getPaymentType().toUpperCase()));
        txn.setTransactionTime(LocalDateTime.now());
        transactionRepo.save(txn);
        return order;
    }

    public void deleteOrder(Long id){
        repository.deleteById(id);
    }

}
