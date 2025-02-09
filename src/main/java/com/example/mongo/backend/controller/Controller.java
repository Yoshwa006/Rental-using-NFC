package com.example.mongo.backend.controller;


import com.example.mongo.backend.Item.Item;
import com.example.mongo.backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class Controller {

    private final ItemService service;

    @Autowired
    public Controller(ItemService service){
        this.service = service;
    }


    @GetMapping
    public List<Item> returnAll(){
        return service.returnAll();
    }
    @GetMapping("/{id}")
    public Optional<Item> getData(@PathVariable String id){
        return service.getDetails(id);
    }

    @PostMapping
    public String registerItem(@RequestBody Item item){
        service.registerItem(item);
        return "https://nfc-rental-system2-1.onrender.com/api/items/" + item.getId();
    }

    @PutMapping("/{itemId}/modify")
    public ResponseEntity<?> modifyItemDetails(@PathVariable String itemId, @RequestBody Item updatedItem) {
        try {
            Item modifiedItem = service.modifyDetails(itemId, updatedItem);
            return ResponseEntity.ok(modifiedItem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{itemId}/delete")
    public ResponseEntity<?> deleteItemDetails(@PathVariable String itemId) {
        boolean deleted = service.itemDelete(itemId);

        if (deleted) {
            return ResponseEntity.ok("Item deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found.");
        }
    }

}