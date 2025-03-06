package com.siblingscup.coffee.repository;

import com.siblingscup.coffee.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sale,Long> {
}
