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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.siblingscup.coffee.dto.OrderItemDTO;
import com.siblingscup.coffee.dto.OrderRequestDTO;
import com.siblingscup.coffee.dto.OrderSummary.OrderItemSummaryDTO;
import com.siblingscup.coffee.dto.OrderSummary.OrderSummaryDTO;

@Service

public class OrderService {

    @Autowired
    private   OrderRepository repository;
    @Autowired
    private CustomerRepository customerRepo;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private SalesRepository saleRepo;
    @Autowired
    private TransactionRepository transactionRepo;
    public List<OrderSummaryDTO>getAllOrders(){
        return  repository.findAll().stream().map(this::mapToResponseDTO).toList();
    }

    public Optional<OrderSummaryDTO>getOrderById(Long id){
        return repository.findById(id).map(this::mapToResponseDTO);
    }

    public Order saveOrder(Order order){
        return repository.save(order);
    }

    private OrderSummaryDTO mapToResponseDTO(Order order){
        OrderSummaryDTO dto=new OrderSummaryDTO();
        dto.setId(order.getId());
        dto.setOrderTime(order.getOrderTime());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());

        List<OrderItemSummaryDTO> itemDTOs=new ArrayList<>();
        for(OrderItem item:order.getItems()){
            OrderItemSummaryDTO itemDto=new OrderItemSummaryDTO();
            itemDto.setId(item.getId());
            itemDto.setProductName(item.getProduct().getName());
            itemDto.setQuantity(item.getQuantity());
            itemDto.setPrice(item.getPrice());
            itemDTOs.add(itemDto);
        }
        dto.setItems(itemDTOs);
        return dto;
    }

    public OrderSummaryDTO createOrder(OrderRequestDTO request){

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
        return mapToResponseDTO(order);
    }

    public void deleteOrder(Long id){
        repository.deleteById(id);
    }

}
