package com.plateer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ShoppingmallCommentBootApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(ShoppingmallCommentBootApplication.class, args);
		log.info("HELLO");
	}
}
