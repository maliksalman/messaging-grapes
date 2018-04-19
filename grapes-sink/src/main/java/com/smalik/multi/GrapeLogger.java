package com.smalik.multi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrapeLogger {

    private static Logger logger = LoggerFactory.getLogger(GrapeLogger.class);

    private String type;

    public GrapeLogger(String type) {
        this.type = type;
    }

    public void log(Grape grape) {
        long millisAlive = System.currentTimeMillis() - grape.getCreatedAt().getTime();
        logger.info(String.format("%s - ID=%s, MillisAlive=%d, Color=%s", type, grape.getId(), millisAlive, grape.getColor()));
    }
}
