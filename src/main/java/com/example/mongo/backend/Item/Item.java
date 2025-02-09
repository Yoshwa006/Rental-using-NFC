package com.example.mongo.backend.Item;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "items") // Specifies the MongoDB collection
public class Item {

    @Id
    private String id; // MongoDB uses String for IDs by default

    private String itemName;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    private List<String> images; // List of image URLs
    private Long price;
    private String description;

    private String ownerName;
    private String phone;
    private boolean isAvailable;
    private String password;

    // Default Constructor
    public Item() {}

    // Parameterized Constructor
    public Item(String itemName, Long price, String description, String ownerName, String phone, boolean isAvailable, String password,List<String> images) {
        this.itemName = itemName;
        this.images=images;
        this.price = price;
        this.description = description;
        this.ownerName = ownerName;
        this.phone = phone;
        this.isAvailable = isAvailable;
        this.password = password;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", phone='" + phone + '\'' +
                ", isAvailable=" + isAvailable +
                ", password='" + password + '\'' +
                '}';
    }
}
