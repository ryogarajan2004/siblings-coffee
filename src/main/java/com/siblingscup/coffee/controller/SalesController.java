package com.siblingscup.coffee.controller;


import com.siblingscup.coffee.model.Sale;
import com.siblingscup.coffee.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    @Autowired
    private SaleService saleService;


    @GetMapping
    public List<Sale>getAllSales(){
        return saleService.getAllSales();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale>getSalesById(@PathVariable Long id){
        Optional<Sale>sale=saleService.getSaleById(id);

        return sale.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public Sale createSale(@RequestBody Sale sale){
        return saleService.saveSale(sale);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSale(@PathVariable Long id){
        saleService.deleteSale(id);
        return ResponseEntity.ok().build();
    }
}
