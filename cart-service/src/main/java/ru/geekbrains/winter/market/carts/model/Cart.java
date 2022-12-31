package ru.geekbrains.winter.market.carts.model;

import lombok.Data;
import ru.geekbrains.winter.api.ProductDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private int totalCost;

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
        totalCost = 0;
    }

    private void recalculate() {
        totalCost = 0;
        for (CartItem item :
                items) {
            totalCost += item.getCost();
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
