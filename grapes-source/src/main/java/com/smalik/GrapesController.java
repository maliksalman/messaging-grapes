package com.smalik;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class GrapesController {

    private final Grapevine grapevine;

    public GrapesController(Grapevine grapevine) {
        this.grapevine = grapevine;
    }

    @PostMapping("/generate/{from}/{to}")
    public List<Grape> generate(@PathVariable("from") int from, @PathVariable("to") int to) {
        return IntStream.range(from, to)
                .mapToObj(index -> {
                    Grape payload = new Grape(index);
                    grapevine.singleGrapeChannel().send(MessageBuilder
                            .withPayload(payload)
                            .build());
                    return payload;
                })
                .collect(Collectors.toList());
    }

    @PostMapping("/generate/{color}")
    public ColorfulGrape generateRed(@PathVariable String color) {
        ColorfulGrape payload = new ColorfulGrape(UUID.randomUUID().toString());
        grapevine.multiGrapesChannel().send(MessageBuilder
                .withPayload(payload)
                .setHeader("color", color)
                .build());
        return payload;
    }
}
