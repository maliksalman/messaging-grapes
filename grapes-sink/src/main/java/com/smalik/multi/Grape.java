package com.smalik.multi;

import java.time.LocalDateTime;

public abstract class Grape {

    private LocalDateTime createdAt;
    private String id;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract String getColor();
}
