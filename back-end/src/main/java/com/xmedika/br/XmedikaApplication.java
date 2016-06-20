package com.xmedika.br;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class XmedikaApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmedikaApplication.class, args);
	}
}
