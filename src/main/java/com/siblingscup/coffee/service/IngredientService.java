package com.siblingscup.coffee.service;

import com.siblingscup.coffee.dto.IngredientDto;
import com.siblingscup.coffee.model.Ingredient;
import com.siblingscup.coffee.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientService {

    @Autowired
    private IngredientRepository repository;

    @Autowired
    private SupplierService supplierService;

    public List<Ingredient> getAllIngredients() {
        return repository.findAll();
    }

    public Optional<Ingredient> getIngredientById(Long id) {
        return repository.findById(id);
    }

    public Ingredient saveIngredient(IngredientDto dto) {
        try{
        Ingredient ingredient=new Ingredient();
        ingredient.setName(dto.getName());
        ingredient.setUnit(dto.getBaseUnit());
        ingredient.setPrice(dto.getPrice());
        ingredient.setConversionFactor(dto.getConversionFactor());
        ingredient.setLowStockThreshold(dto.getLowStockThreshold());
        ingredient.setStockQuantity(dto.getStockQuantity());

        ingredient.setSupplier(supplierService.getSupplierById(dto.getSupplierId()).get());
       return   repository.save(ingredient);
    }catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
    }

    public void deleteIngredient(Long id) {
        repository.deleteById(id);
    }

    public Optional<Ingredient> updateIngredient(Long id, Ingredient updatedIngredient) {
        return repository.findById(id).map(existingIngredient -> {
            if (updatedIngredient.getName() != null) {
                existingIngredient.setName(updatedIngredient.getName());
            }
            if (updatedIngredient.getUnit() != null) {
                existingIngredient.setUnit(updatedIngredient.getUnit());
            }
            if (updatedIngredient.getPrice() != 0) {
                existingIngredient.setPrice(updatedIngredient.getPrice());
            }
            if (updatedIngredient.getSupplier() != null) {
                existingIngredient.setSupplier(updatedIngredient.getSupplier());
            }
            if (updatedIngredient.getLowStockThreshold() != 0) {
                existingIngredient.setLowStockThreshold(updatedIngredient.getLowStockThreshold());
            }
            if (updatedIngredient.getStockQuantity() != 0) {
                existingIngredient.setStockQuantity(updatedIngredient.getStockQuantity());
            }
            return repository.save(existingIngredient);
        });
    }


}
