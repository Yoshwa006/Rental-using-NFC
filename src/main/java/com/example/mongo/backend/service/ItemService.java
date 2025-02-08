package com.example.mongo.backend.service;


import com.example.mongo.backend.Item.Item;
import com.example.mongo.backend.repo.ItemRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepo repo;

    public ItemService(ItemRepo repo){
        this.repo = repo;
    }

    public Item registerItem(Item item){
        return repo.save(item);
    }

    public Optional<Item> getDetails(String id){
        return repo.findById(id);
    }

    public Item modifyDetails(String itemId, Item updatedItem) {
        Optional<Item> existingItemOptional = repo.findById(itemId);

        if (existingItemOptional.isPresent()) {
            Item existingItem = existingItemOptional.get();

            if (existingItem.getPassword().equals(updatedItem.getPassword())) {
                existingItem.setItemName(updatedItem.getItemName());
                existingItem.setPrice(updatedItem.getPrice());
                existingItem.setDescription(updatedItem.getDescription());
                existingItem.setOwnerName(updatedItem.getOwnerName());
                existingItem.setPhone(updatedItem.getPhone());
                existingItem.setAvailable(updatedItem.isAvailable());

                // Save updated item
                return repo.save(existingItem);
            } else {
                throw new IllegalArgumentException("Invalid password. Modification not allowed.");
            }
        } else {
            throw new IllegalArgumentException("Item not found.");
        }
    }
        public List<Item> returnAll(){
            return repo.findAll();
        }
    }
