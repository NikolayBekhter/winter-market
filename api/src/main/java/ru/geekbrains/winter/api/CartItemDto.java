package ru.geekbrains.winter.api;

public class CartItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private int costPerProduct;
    private int cost;

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

    public int getCostPerProduct() {
        return costPerProduct;
    }

    public void setCostPerProduct(int costPerProduct) {
        this.costPerProduct = costPerProduct;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
