package com.siblingscup.coffee.service;

import com.siblingscup.coffee.model.Sale;
import com.siblingscup.coffee.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleService {
    private  SalesRepository repository;



    public List<Sale> getAllSales() {
        return repository.findAll();
    }

    public Optional<Sale> getSaleById(Long id) {
        return repository.findById(id);
    }

    public Sale saveSale(Sale sale) {
        return repository.save(sale);
    }

    public Optional<Sale> updateSale(Long id, Sale updatedSale) {
        return repository.findById(id).map(existingSale -> {
            if (updatedSale.getOrder() != null) {
                existingSale.setOrder(updatedSale.getOrder());
            }
            if (updatedSale.getTotalAmount() != 0) {
                existingSale.setTotalAmount(updatedSale.getTotalAmount());
            }
            return repository.save(existingSale);
        });
    }

    public void deleteSale(Long id) {
        repository.deleteById(id);
    }
}

