package com.example.ishoppinglist.code.models;

import java.io.Serializable;

public class Category implements Serializable {
    private long id;
    private String name;
    private String description;
    private String colorHex;

    public Category() {}

    public Category(long id, String name, String description, String colorHex) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.colorHex = colorHex;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getColorHex() { return colorHex; }
    public void setColorHex(String colorHex) { this.colorHex = colorHex; }
}