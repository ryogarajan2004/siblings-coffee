package com.siblingscup.coffee.controller;

import com.siblingscup.coffee.dto.IngredientDto;
import com.siblingscup.coffee.model.Ingredient;
import com.siblingscup.coffee.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public List<Ingredient> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable Long id) {
        Optional<Ingredient> ingredient = new IngredientService().getIngredientById(id);
        return ingredient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createIngridient(@RequestBody IngredientDto ingredient) {
     ingredientService.saveIngredient(ingredient);
     return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable Long id, @RequestBody Ingredient updatedIngridient) {
        return ingredientService.updateIngredient(id, updatedIngridient).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteIngredient(@PathVariable Long id){
         ingredientService.deleteIngredient(id);

         return  ResponseEntity.ok().build();
    }
}
