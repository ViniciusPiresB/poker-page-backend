package com.example.pokerpage;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Poker Page", version = "0.1", description = "API Poker Page"))
public class PokerPageApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokerPageApplication.class, args);
	}

}
