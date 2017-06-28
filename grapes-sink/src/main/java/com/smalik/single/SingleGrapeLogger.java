package com.smalik.single;

import org.joda.time.LocalDateTime;
import org.joda.time.Period;
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
        long millisAlive2 = Period.fieldDifference(payload.getAnotherTime(), new LocalDateTime()).getMillis();
        logger.info(String.format("%s - Index=%d, ID=%s, MillisAlive=%d, MillisAliveOther=%d, MissingVerified=%s", type, payload.getIndex(), payload.getId(), millisAlive, millisAlive2, payload.verifyMissing()));
    }
}
