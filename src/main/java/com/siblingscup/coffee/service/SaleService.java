package com.siblingscup.coffee.service;

import com.siblingscup.coffee.model.Sale;
import com.siblingscup.coffee.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    private  Sales saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Optional<Sale> getSaleById(Long id) {
        return saleRepository.findById(id);
    }

    public Sale saveSale(Sale sale) {
        return saleRepository.save(sale);
    }

    public Optional<Sale> updateSale(Long id, Sale updatedSale) {
        return saleRepository.findById(id).map(existingSale -> {
            if (updatedSale.getOrder() != null) {
                existingSale.setOrder(updatedSale.getOrder());
            }
            if (updatedSale.getTotalAmount() != 0) {
                existingSale.setTotalAmount(updatedSale.getTotalAmount());
            }
            return saleRepository.save(existingSale);
        });
    }

    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }
}

