package com.hooeats.api.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Item {
    private String itemId;
    private String name;
    private BigDecimal price;
    private LocalDate dateAdded;

    public Item() {}

    public Item(String itemId, String name, BigDecimal price, LocalDate dateAdded) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.dateAdded = dateAdded;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }
}
