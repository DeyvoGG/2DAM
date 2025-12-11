package com.example.ishoppinglist.code.models;

import java.io.Serializable;

public class ShoppingList implements Serializable {
    private long id;
    private String name;
    private long userId;
    private String createdAt;

    public ShoppingList() {}

    public ShoppingList(long id, String name, long userId, String createdAt) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}