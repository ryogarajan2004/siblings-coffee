package com.siblingscup.coffee.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "supplier_order")
public class SupplierOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id",nullable = false)
    private Supplier supplier;

    private LocalDateTime orderDate;
    private String status;

    @OneToMany(mappedBy = "supplierOrder",cascade = CascadeType.ALL)
    private List<SupplierOrderItem>orderItems;
}
