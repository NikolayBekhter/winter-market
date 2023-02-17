package ru.geekbrains.winter.market.core.api;

import java.math.BigDecimal;

public class CartItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal costPerProduct;
    private BigDecimal cost;

    public CartItemDto() {
    }

    public CartItemDto(Long productId, String productTitle, int quantity, BigDecimal costPerProduct, BigDecimal cost) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.costPerProduct = costPerProduct;
        this.cost = cost;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCostPerProduct() {
        return costPerProduct;
    }

    public void setCostPerProduct(BigDecimal costPerProduct) {
        this.costPerProduct = costPerProduct;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
