package com.smalik.multi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class MultiGrapeListener {

    private Logger logger = LoggerFactory.getLogger(MultiGrapeListener.class);

    @StreamListener(value=MultiGrapeSink.MULTI_GRAPE, condition = "headers['color']=='green'")
    public void listenAndLog(@Payload GreenGrape grape) {
        log(grape);
    }

    @StreamListener(value=MultiGrapeSink.MULTI_GRAPE, condition = "headers['color']=='red'")
    public void listenAndLog(@Payload RedGrape grape) {
        log(grape);
    }

    void log(Grape grape) {
        long nanosAlive = Duration.between(grape.getCreatedAt(), LocalDateTime.now()).getNano();
        logger.info(String.format("ID=%s, MillisAlive=%d, Color=%s", grape.getId(), nanosAlive/1000000, grape.getColor()));
    }
}
