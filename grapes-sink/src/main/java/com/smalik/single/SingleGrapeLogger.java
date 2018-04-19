package com.smalik.single;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingleGrapeLogger {

    private static Logger logger = LoggerFactory.getLogger(SingleGrapeLogger.class);

    private String type;

    public SingleGrapeLogger(String type) {
        this.type = type;
    }

    public void log(SingleGrape payload) {
        long millisAlive = System.currentTimeMillis() - payload.getGeneratedAt().getTime();
        logger.info(String.format("%s - Index=%d, ID=%s, MillisAlive=%d, MissingVerified=%s", type, payload.getIndex(), payload.getId(), millisAlive, payload.verifyMissing()));
    }
}
