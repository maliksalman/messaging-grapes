package com.smalik;

import java.time.LocalDateTime;
import java.util.UUID;

public class Grape {

    private LocalDateTime generatedAt;
    private long index;
    private String id;
    private String extra;

    public Grape() {
    }

    public Grape(long index) {
        this.index = index;
        this.generatedAt = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
        this.extra = "something-extra";
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
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

    public void setId(String id) {
        this.id = id;
    }
}
