
package com.siblingscup.coffee.service;

import com.siblingscup.coffee.model.Supplier;
import com.siblingscup.coffee.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private  SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }

    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Optional<Supplier> updateSupplier(Long id, Supplier updatedSupplier) {
        return supplierRepository.findById(id).map(existingSupplier -> {
            if (updatedSupplier.getName() != null) {
                existingSupplier.setName(updatedSupplier.getName());
            }
            if (updatedSupplier.getContact() != null) {
                existingSupplier.setContact(updatedSupplier.getContact());
            }
            return supplierRepository.save(existingSupplier);
        });
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}
