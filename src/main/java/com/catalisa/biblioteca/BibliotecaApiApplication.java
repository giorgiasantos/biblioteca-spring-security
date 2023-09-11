package com.catalisa.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BibliotecaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApiApplication.class, args);
	}

}
