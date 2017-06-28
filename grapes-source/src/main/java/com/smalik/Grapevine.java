package com.smalik;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Grapevine {

    String SINGLE_GRAPE = "single-grape";

    @Output(Grapevine.SINGLE_GRAPE)
    MessageChannel singleGrapeChannel();


    String MULTI_GRAPES = "multi-grapes";

    @Output(Grapevine.MULTI_GRAPES)
    MessageChannel multiGrapesChannel();
}
