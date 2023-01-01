package ru.geekbrains.winter.market.carts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal costPerProduct;
    private BigDecimal cost;

    public void changeQuantity(int delta) {
        quantity += delta;
        cost = costPerProduct.multiply(BigDecimal.valueOf(quantity));
    }
}
