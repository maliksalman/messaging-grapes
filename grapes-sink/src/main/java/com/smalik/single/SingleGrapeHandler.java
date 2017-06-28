package com.smalik.single;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;

import java.io.IOException;

public class SingleGrapeHandler implements org.springframework.messaging.MessageHandler {

    private SingleGrapeLogger paylodLogger = new SingleGrapeLogger("HANDLER");

    @Autowired
    private ObjectMapper mapper;

    public SingleGrapeHandler(SubscribableChannel channel) {
        channel.subscribe(this);
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        String payloadString = (String) message.getPayload();
        try {
            SingleGrape payload = mapper.readValue(payloadString, SingleGrape.class);
            paylodLogger.log(payload);
        } catch(IOException e) {
            throw new MessagingException(e.getMessage(), e);
        }
    }
}
