package com.smalik.single;

import org.joda.time.LocalDateTime;

import java.util.Date;

public class SingleGrape {

    private Date generatedAt;
    private LocalDateTime anotherTime;
    private long index;
    private String id;
    private String missing;

    public LocalDateTime getAnotherTime() {
        return anotherTime;
    }

    public void setAnotherTime(LocalDateTime anotherTime) {
        this.anotherTime = anotherTime;
    }

    public Date getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(Date generatedAt) {
        this.generatedAt = generatedAt;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMissing() {
        return missing;
    }

    public void setMissing(String missing) {
        this.missing = missing;
    }

    public boolean verifyMissing() {
        return missing == null;
    }
}
