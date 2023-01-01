package ru.geekbrains.winter.market.carts.model;

import lombok.Data;
import ru.geekbrains.winter.api.ProductDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private BigDecimal totalCost;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(ProductDto product) {
        for (CartItem item :
                items) {
            if (product.getId().equals(item.getProductId())) {
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getCost(), product.getCost()));
        recalculate();
    }

    public void remove(Long productId) {
        if (items.removeIf(item -> item.getProductId().equals(productId))) {
            recalculate();
        }
    }

    public void clear() {
        items.clear();
        totalCost = new BigDecimal("0.00");
    }

    private void recalculate() {
        totalCost = new BigDecimal("0.00");
        for (CartItem item :
                items) {
            totalCost = totalCost.add(item.getCost()).setScale(2, RoundingMode.HALF_UP);
        }
    }

    public void changeQuantity(ProductDto product, Integer delta) {
        for (CartItem item :
                items) {
            if (product.getId().equals(item.getProductId())) {
                item.changeQuantity(delta);
                if (item.getQuantity() == 0) {
                    remove(product.getId());
                    return;
                }
                recalculate();
                return;
            }
        }
        recalculate();
    }
}
