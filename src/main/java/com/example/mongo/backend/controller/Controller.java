package com.example.mongo.backend.controller;

import com.example.mongo.backend.dto.ItemRequestDTO;
import com.example.mongo.backend.dto.ItemResponseDTO;
import com.example.mongo.backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/items")
public class Controller {

    private final ItemService service;

    @Autowired
    public Controller(ItemService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> returnAll() {
        return ResponseEntity.ok(service.returnAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getData(@PathVariable String id) {
        Optional<?> item = service.getDetails(id);
        if (item.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Check again ID");
        }
        return ResponseEntity.ok(item.get());
    }

    @PostMapping
    public ResponseEntity<String> registerItem(@RequestBody ItemRequestDTO itemDTO) {
        ItemResponseDTO savedItem = service.registerItem(itemDTO);
        String url = "https://yoshwa006.github.io/Rental-System-using-NFC/?id=" + savedItem.getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<?> modifyItemDetails(@PathVariable String itemId, @RequestBody ItemRequestDTO updatedDTO) {
        try {
            ItemResponseDTO modifiedItem = service.modifyDetails(itemId, updatedDTO);
            return ResponseEntity.ok(modifiedItem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> deleteItemDetails(@PathVariable String itemId) {
        boolean deleted = service.itemDelete(itemId);
        if (deleted) {
            return ResponseEntity.ok("Item deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found.");
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {
        service.deleteAll();
        return ResponseEntity.ok("Deleted All!");
    }
}
