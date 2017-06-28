package com.smalik;

import java.util.Date;

public class ColorfulGrape {

    private Date createdAt;
    private String id;

    public ColorfulGrape(String id) {
        this.id = id;
        this.createdAt = new Date();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getId() {
        return id;
    }
}
