package com.smalik.multi;

import java.util.Date;

public abstract class Grape {

    private Date createdAt;
    private String id;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
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
