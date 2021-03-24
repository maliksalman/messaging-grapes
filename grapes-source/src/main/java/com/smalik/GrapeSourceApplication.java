package com.smalik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(Grapevine.class)
public class GrapeSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrapeSourceApplication.class, args);
	}
}
