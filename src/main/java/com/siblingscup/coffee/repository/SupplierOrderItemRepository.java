package com.siblingscup.coffee.repository;

import com.siblingscup.coffee.model.SupplierOrder;
import com.siblingscup.coffee.model.SupplierOrderItem;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierOrderItemRepository extends JpaRepository<SupplierOrderItem, Long> {
    // Define custom query methods here if needed
}