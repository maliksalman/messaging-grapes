package com.smalik.single;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

public class SingleGrapeListener {

    private SingleGrapeLogger paylodLogger = new SingleGrapeLogger("LISTENER");

    @StreamListener(SingleGrapeSink.SINGLE_GRAPE)
    public void listenAndLog(@Payload SingleGrape payload) {
        paylodLogger.log(payload);
    }
}
