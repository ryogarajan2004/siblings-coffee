package com.siblingscup.coffee.repository;

import com.siblingscup.coffee.model.SupplierOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierOrderRepository extends JpaRepository<SupplierOrder,Long> {
//    List<SupplierOrder> findByStatus(String  status);
}
