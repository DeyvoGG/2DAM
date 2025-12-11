package com.example.ishoppinglist.code.models;

import java.io.Serializable;

public class Product implements Serializable {
    private long id;
    private String name;
    private String description;
    private boolean isCompleted;
    private long shoppingListId;
    private long categoryId;

    public Product() {}

    public Product(long id, String name, String description, boolean isCompleted, long shoppingListId, long categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isCompleted = isCompleted;
        this.shoppingListId = shoppingListId;
        this.categoryId = categoryId;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    public long getShoppingListId() { return shoppingListId; }
    public void setShoppingListId(long shoppingListId) { this.shoppingListId = shoppingListId; }

    public long getCategoryId() { return categoryId; }
    public void setCategoryId(long categoryId) { this.categoryId = categoryId; }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isCompleted=" + isCompleted +
                ", shoppingListId=" + shoppingListId +
                ", categoryId=" + categoryId +
                '}';
    }
}