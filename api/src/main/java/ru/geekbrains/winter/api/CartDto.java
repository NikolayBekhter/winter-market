package ru.geekbrains.winter.api;

import java.util.List;

public class CartDto {
    private List<CartItemDto> items;
    private int totalCost;

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
