package com.org.ticketgenerate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TokengenerateApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokengenerateApplication.class, args);
	}

}
