package com.hooeats.api.controller;

import com.hooeats.api.entity.Ingredient;
import com.hooeats.api.repository.IngredientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
@CrossOrigin(origins = "*")
public class IngredientController {

    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable("id") String id) {
        return ingredientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createIngredient(@RequestBody Ingredient ingredient) {
        int result = ingredientRepository.save(ingredient);
        if (result > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Ingredient created successfully");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create ingredient");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateIngredient(@PathVariable("id") String id, @RequestBody Ingredient ingredient) {
        ingredient.setIngredientId(id);
        int result = ingredientRepository.update(ingredient);
        if (result > 0) {
            return ResponseEntity.ok("Ingredient updated successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable("id") String id) {
        int result = ingredientRepository.deleteById(id);
        if (result > 0) {
            return ResponseEntity.ok("Ingredient deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
