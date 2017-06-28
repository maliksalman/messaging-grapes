package com.smalik.single;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface SingleGrapeSink {

    String SINGLE_GRAPE = "single-grape";

    @Input(SingleGrapeSink.SINGLE_GRAPE)
    SubscribableChannel input();
}
