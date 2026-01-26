package com.hooeats.api.controller;

import com.hooeats.api.entity.Item;
import com.hooeats.api.entity.Vendor;
import com.hooeats.api.repository.ItemRepository;
import com.hooeats.api.repository.VendorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@CrossOrigin(origins = "*")
public class VendorController {

    private final VendorRepository vendorRepository;
    private final ItemRepository itemRepository;

    public VendorController(VendorRepository vendorRepository, ItemRepository itemRepository) {
        this.vendorRepository = vendorRepository;
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable("id") String id) {
        return vendorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createVendor(@RequestBody Vendor vendor) {
        int result = vendorRepository.save(vendor);
        if (result > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Vendor created successfully");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create vendor");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateVendor(@PathVariable("id") String id, @RequestBody Vendor vendor) {
        vendor.setVendorId(id);
        int result = vendorRepository.update(vendor);
        if (result > 0) {
            return ResponseEntity.ok("Vendor updated successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVendor(@PathVariable("id") String id) {
        int result = vendorRepository.deleteById(id);
        if (result > 0) {
            return ResponseEntity.ok("Vendor deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<Item>> getItemsByVendorId(@PathVariable("id") String id) {
        if (vendorRepository.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Item> items = itemRepository.findItemsByVendorId(id);
        return ResponseEntity.ok(items);
    }
}
