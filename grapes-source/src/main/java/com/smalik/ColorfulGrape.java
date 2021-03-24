package com.smalik;

import java.time.LocalDateTime;

public class ColorfulGrape {

    private LocalDateTime createdAt;
    private String id;

    public ColorfulGrape(String id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getId() {
        return id;
    }
}
