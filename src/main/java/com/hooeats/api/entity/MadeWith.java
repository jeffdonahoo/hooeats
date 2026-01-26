package com.hooeats.api.entity;

public class MadeWith {
    private String itemId;
    private String ingredientId;
    private Integer quantity;

    public MadeWith() {}

    public MadeWith(String itemId, String ingredientId, Integer quantity) {
        this.itemId = itemId;
        this.ingredientId = ingredientId;
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(String ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
