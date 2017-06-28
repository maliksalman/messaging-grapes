package com.smalik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@RestController
@EnableBinding(Grapevine.class)
public class AmqpSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmqpSourceApplication.class, args);
	}

	@Autowired
	private Grapevine grapevine;

	@PostMapping("/generate/{from}/{to}")
	public List<Grape> generate(@PathVariable long from, @PathVariable long to) {
		ArrayList<Grape> messages = new ArrayList<>();
		for (long i = from; i < to; i++) {
			Grape payload = new Grape(i);
			messages.add(payload);
			grapevine.singleGrapeChannel().send(MessageBuilder.withPayload(payload).build());
		}
		return messages;
	}

	@PostMapping("/generate/{color}")
	public ColorfulGrape generateRed(@PathVariable String color) {
		ColorfulGrape payload = new ColorfulGrape(UUID.randomUUID().toString());
		grapevine.multiGrapesChannel().send(MessageBuilder.withPayload(payload).setHeader("color", color).build());
		return payload;
	}
}
