package com.example.mongo.backend.dto;

import com.example.mongo.backend.Item.Item;
import com.example.mongo.backend.dto.ItemRequestDTO;
import com.example.mongo.backend.dto.ItemResponseDTO;

public class ItemMapper {

    public static Item toItem(ItemRequestDTO dto) {
        Item item = new Item();
        item.setItemName(dto.getItemName());
        item.setPrice(dto.getPrice());
        item.setPricePerDay(dto.getPricePerDay());
        item.setDescription(dto.getDescription());
        item.setOwnerName(dto.getOwnerName());
        item.setPhone(dto.getPhone());
        item.setAvailable(dto.isAvailable());
        item.setPassword(dto.getPassword());
        item.setDateOfUpload(dto.getDateOfUpload());
        item.setImages(dto.getImages());
        return item;
    }

    public static ItemResponseDTO toResponseDTO(Item item) {
        ItemResponseDTO dto = new ItemResponseDTO();
        dto.setId(item.getId());
        dto.setItemName(item.getItemName());
        dto.setPrice(item.getPrice());
        dto.setPricePerDay(item.getPricePerDay());
        dto.setDescription(item.getDescription());
        dto.setOwnerName(item.getOwnerName());
        dto.setPhone(item.getPhone());
        dto.setAvailable(item.isAvailable());
        dto.setDateOfUpload(item.getDateOfUpload());
        dto.setImages(item.getImages());
        return dto;
    }
}
