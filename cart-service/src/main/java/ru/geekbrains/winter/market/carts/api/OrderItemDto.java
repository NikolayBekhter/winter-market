package ru.geekbrains.winter.market.carts.api;

import java.math.BigDecimal;

public class OrderItemDto {
    private Long id;
    private String productTitle;
    private Long orderId;
    private int quantity;
    private BigDecimal costPerProduct;
    private BigDecimal cost;

    public OrderItemDto() {
    }

    public OrderItemDto(Long id, String productTitle, Long orderId, int quantity, BigDecimal costPerProduct, BigDecimal cost) {
        this.id = id;
        this.productTitle = productTitle;
        this.orderId = orderId;
        this.quantity = quantity;
        this.costPerProduct = costPerProduct;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    @Override
    public String toString() {
        return "OrderItemDto{" +
                "id=" + id +
                ", productTitle='" + productTitle + '\'' +
                ", orderId=" + orderId +
                ", quantity=" + quantity +
                ", costPerProduct=" + costPerProduct +
                ", cost=" + cost +
                '}';
    }
}
