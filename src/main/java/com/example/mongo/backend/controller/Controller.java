package com.example.mongo.backend.controller;

import com.example.mongo.backend.Item.Item;
import com.example.mongo.backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/items") // BASE PATH
public class Controller {

    private final ItemService service;

    @Autowired
    public Controller(ItemService service) {
        this.service = service;
    }

    // Get all items
    @GetMapping
    public List<Item> returnAll() {
        return service.returnAll();
    }

    // Get item by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getData(@PathVariable String id) {
        Optional<Item> item = service.getDetails(id);
        if (item.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Check again ID");
        }
        return ResponseEntity.ok(item.get()); // Fix: `.get()` to avoid returning Optional<>
    }

    // Register a new item
    @PostMapping
    public ResponseEntity<String> registerItem(@RequestBody Item item) {
        service.registerItem(item);
        String url = "https://nfc-rental-system2-1.onrender.com/items/" + item.getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }

    // Modify item details
    @PutMapping("/{itemId}")
    public ResponseEntity<?> modifyItemDetails(@PathVariable String itemId, @RequestBody Item updatedItem) {
        try {
            Item modifiedItem = service.modifyDetails(itemId, updatedItem);
            return ResponseEntity.ok(modifiedItem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Delete item
    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> deleteItemDetails(@PathVariable String itemId) {
        boolean deleted = service.itemDelete(itemId);
        if (deleted) {
            return ResponseEntity.ok("Item deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found.");
        }
    }
}
