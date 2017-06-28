package com.smalik;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

import java.util.Date;
import java.util.UUID;

public class Grape {

    private Date generatedAt;
    private LocalDateTime anotherTime;
    private long index;
    private String id;
    private String extra;
    private String empty;

    public Grape() {
    }

    public Grape(long index) {
        this.index = index;
        this.generatedAt = new Date();
        this.anotherTime = LocalDateTime.now(DateTimeZone.UTC);
        this.id = UUID.randomUUID().toString();
        this.extra = "something-extra";
    }

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

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }

    public void setId(String id) {
        this.id = id;
    }
}
