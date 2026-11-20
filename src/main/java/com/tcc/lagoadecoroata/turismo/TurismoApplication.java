package com.tcc.lagoadecoroata.turismo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TurismoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TurismoApplication.class, args);
	}

}
