package com.example.mongo.backend.repo;

import com.example.mongo.backend.Item.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepo extends MongoRepository<Item, String> {
}
