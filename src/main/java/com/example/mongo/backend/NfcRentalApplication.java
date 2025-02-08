package com.example.mongo.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class NfcRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(NfcRentalApplication.class, args);
	}

}
