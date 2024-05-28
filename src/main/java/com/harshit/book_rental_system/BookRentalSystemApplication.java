package com.harshit.book_rental_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class BookRentalSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookRentalSystemApplication.class, args);
	}

}
