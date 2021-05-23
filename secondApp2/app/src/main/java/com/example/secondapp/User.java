package com.example.secondapp;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    private String userName;
    private String userLastName;
    private UUID uuid;
    private String phone;

    public User() {
        this(UUID.randomUUID());
    }

    public User(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getPhone() {
        return phone;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
