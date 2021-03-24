package com.smalik.single;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class SingleGrapeListener {

    private Logger logger = LoggerFactory.getLogger(SingleGrapeListener.class);

    @StreamListener(SingleGrapeSink.SINGLE_GRAPE)
    public void listenAndLog(@Payload SingleGrape payload) {
        long nanosAlive = Duration.between(payload.getGeneratedAt(), LocalDateTime.now()).getNano();
        logger.info(String.format("Index=%d, ID=%s, MillisAlive=%s", payload.getIndex(), payload.getId(), nanosAlive/1000000));
    }
}
