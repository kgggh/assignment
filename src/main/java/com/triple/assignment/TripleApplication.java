package com.triple.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
public class TripleApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripleApplication.class, args);
	}

}
