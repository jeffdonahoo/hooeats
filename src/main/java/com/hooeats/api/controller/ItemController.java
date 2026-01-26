package com.hooeats.api.controller;

import com.hooeats.api.entity.Item;
import com.hooeats.api.repository.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "*")
public class ItemController {

    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") String id) {
        return itemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createItem(@RequestBody Item item) {
        int result = itemRepository.save(item);
        if (result > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Item created successfully");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create item");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@PathVariable("id") String id, @RequestBody Item item) {
        item.setItemId(id);
        int result = itemRepository.update(item);
        if (result > 0) {
            return ResponseEntity.ok("Item updated successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id") String id) {
        int result = itemRepository.deleteById(id);
        if (result > 0) {
            return ResponseEntity.ok("Item deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
