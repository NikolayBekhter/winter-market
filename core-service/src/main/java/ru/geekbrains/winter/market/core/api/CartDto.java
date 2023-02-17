package ru.geekbrains.winter.market.core.api;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {
    private List<CartItemDto> items;
    private BigDecimal totalCost;

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
