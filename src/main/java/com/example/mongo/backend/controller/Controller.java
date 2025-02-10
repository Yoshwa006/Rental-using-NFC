package com.example.mongo.backend.controller;


import com.example.mongo.backend.Item.Item;
import com.example.mongo.backend.service.ItemService;
import com.example.mongo.backend.service.QrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class Controller {

    private final ItemService service;
    private final QrService qr;

    @Autowired
    public Controller(ItemService service, QrService qr){
        this.service = service;
        this.qr = qr;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getData(@PathVariable String id){
        Optional<Item> item = service.getDetails(id);
        if(item.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Check again ID");
        }
        return ResponseEntity.ok(item);
    }


    @PostMapping
    public ResponseEntity<?> registerItem(@RequestBody Item item) {
        service.registerItem(item);

        String fileName = item.getId() + ".png";
        String directory = "src/main/resources/static/";
        String filePath = directory + fileName;

        try {
            QrService.generateQRCode("https://nfc-rental-system2-1.onrender.com/api/items/" + item.getId(), filePath);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating QR Code");
        }

        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/static/")
                .path(fileName)
                .toUriString();

        return ResponseEntity.ok(fileUrl);
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