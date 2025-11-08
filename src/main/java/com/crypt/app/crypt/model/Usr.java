package com.crypt.app.crypt.model;

import java.util.List;

public class Usr {
    private String userId;
    private List<String> devices;

    public Usr() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getDevices() {
        return devices;
    }

    public void setDevices(List<String> devices) {
        this.devices = devices;
    }
}
