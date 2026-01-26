package com.hooeats.api.entity;

import java.math.BigDecimal;

public class Ingredient {
    private String ingredientId;
    private String name;
    private String unit;
    private BigDecimal unitPrice;
    private String foodGroup;
    private Integer inventory;
    private String vendorId;

    public Ingredient() {}

    public Ingredient(String ingredientId, String name, String unit, BigDecimal unitPrice,
                      String foodGroup, Integer inventory, String vendorId) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.foodGroup = foodGroup;
        this.inventory = inventory;
        this.vendorId = vendorId;
    }

    public String getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(String ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getFoodGroup() {
        return foodGroup;
    }

    public void setFoodGroup(String foodGroup) {
        this.foodGroup = foodGroup;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
}
