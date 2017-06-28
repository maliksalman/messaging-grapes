package com.smalik.multi;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

public class MultiGrapeListener {

    private GrapeLogger grapeLogger = new GrapeLogger("COLOR-LISTENER");

    @StreamListener(value=MultiGrapeSink.MULTI_GRAPE, condition = "headers['color']=='green'")
    public void listenAndLog(@Payload GreenGrape grape, @Header(name = "color") String color) {
        grapeLogger.log(grape);
    }

    @StreamListener(value=MultiGrapeSink.MULTI_GRAPE, condition = "headers['color']=='red'")
    public void listenAndLog(@Payload RedGrape grape) {
        grapeLogger.log(grape);
    }
}
