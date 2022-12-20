package ru.geekbrains.spring.winter.market.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long productId;
    private String productTitle;
    private int quantity;
    private int costPerProduct;
    private int cost;

    public void changeQuantity(int delta) {
        quantity += delta;
        cost = costPerProduct * quantity;
    }
}
