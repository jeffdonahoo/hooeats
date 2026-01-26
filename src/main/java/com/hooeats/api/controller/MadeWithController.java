package com.hooeats.api.controller;

import com.hooeats.api.entity.MadeWith;
import com.hooeats.api.repository.MadeWithRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/madewith")
@CrossOrigin(origins = "*")
public class MadeWithController {

    private final MadeWithRepository madeWithRepository;

    public MadeWithController(MadeWithRepository madeWithRepository) {
        this.madeWithRepository = madeWithRepository;
    }

    @GetMapping
    public List<MadeWith> getAllMadeWith() {
        return madeWithRepository.findAll();
    }

    @GetMapping("/item/{itemId}")
    public List<MadeWith> getByItemId(@PathVariable("itemId") String itemId) {
        return madeWithRepository.findByItemId(itemId);
    }

    @GetMapping("/ingredient/{ingredientId}")
    public List<MadeWith> getByIngredientId(@PathVariable("ingredientId") String ingredientId) {
        return madeWithRepository.findByIngredientId(ingredientId);
    }

    @GetMapping("/{itemId}/{ingredientId}")
    public ResponseEntity<MadeWith> getMadeWithById(
            @PathVariable("itemId") String itemId,
            @PathVariable("ingredientId") String ingredientId) {
        return madeWithRepository.findById(itemId, ingredientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createMadeWith(@RequestBody MadeWith madeWith) {
        int result = madeWithRepository.save(madeWith);
        if (result > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("MadeWith record created successfully");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create MadeWith record");
    }

    @PutMapping("/{itemId}/{ingredientId}")
    public ResponseEntity<String> updateMadeWith(
            @PathVariable("itemId") String itemId,
            @PathVariable("ingredientId") String ingredientId,
            @RequestBody MadeWith madeWith) {
        madeWith.setItemId(itemId);
        madeWith.setIngredientId(ingredientId);
        int result = madeWithRepository.update(madeWith);
        if (result > 0) {
            return ResponseEntity.ok("MadeWith record updated successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{itemId}/{ingredientId}")
    public ResponseEntity<String> deleteMadeWith(
            @PathVariable("itemId") String itemId,
            @PathVariable("ingredientId") String ingredientId) {
        int result = madeWithRepository.deleteById(itemId, ingredientId);
        if (result > 0) {
            return ResponseEntity.ok("MadeWith record deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
