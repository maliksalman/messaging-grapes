package com.smalik.multi;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MultiGrapeSink {

    String MULTI_GRAPE = "multi-grapes";

    @Input(MultiGrapeSink.MULTI_GRAPE)
    SubscribableChannel input();
}
