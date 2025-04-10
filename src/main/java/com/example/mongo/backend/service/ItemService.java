package com.example.mongo.backend.service;

import com.example.mongo.backend.Item.Item;
import com.example.mongo.backend.dto.ItemMapper;
import com.example.mongo.backend.dto.ItemRequestDTO;
import com.example.mongo.backend.dto.ItemResponseDTO;
import com.example.mongo.backend.repo.ItemRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepo repo;

    public ItemService(ItemRepo repo) {
        this.repo = repo;
    }

    // Register a new item
    public ItemResponseDTO registerItem(ItemRequestDTO dto) {
        Item item = ItemMapper.toItem(dto);
        repo.save(item);
        return ItemMapper.toResponseDTO(item);
    }

    // Get item details by ID
    public Optional<Item> getDetails(String id) {
        return repo.findById(id);
    }

    // Modify an existing item based on ID and DTO
    public ItemResponseDTO modifyDetails(String itemId, ItemRequestDTO requestDTO) {
        Optional<Item> existingItemOptional = repo.findById(itemId);

        if (existingItemOptional.isPresent()) {
            Item existingItem = existingItemOptional.get();

            // Password check
            if (existingItem.getPassword().equals(requestDTO.getPassword())) {
                // Update values
                existingItem.setItemName(requestDTO.getItemName());
                existingItem.setPrice(requestDTO.getPrice());
                existingItem.setDescription(requestDTO.getDescription());
                existingItem.setOwnerName(requestDTO.getOwnerName());
                existingItem.setPhone(requestDTO.getPhone());
                existingItem.setAvailable(requestDTO.isAvailable());
                existingItem.setDateOfUpload(requestDTO.getDateOfUpload());
                existingItem.setPricePerDay(requestDTO.getPricePerDay());
                existingItem.setImages(requestDTO.getImages());

                Item updatedItem = repo.save(existingItem);
                return ItemMapper.toResponseDTO(updatedItem);
            } else {
                throw new IllegalArgumentException("Invalid password. Modification not allowed.");
            }
        } else {
            throw new IllegalArgumentException("Item not found.");
        }
    }

    // Return all items
    public List<Item> returnAll() {
        return repo.findAll();
    }

    // Delete item by ID
    public boolean itemDelete(String id) {
        repo.deleteById(id);
        return true;
    }

    // Delete all items
    public void deleteAll() {
        repo.deleteAll();
    }
}
