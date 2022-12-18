package ru.geekbrains.spring.winter.market.dtos;

import lombok.Data;
import ru.geekbrains.spring.winter.market.entities.Product;

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

    public void add(Product product) {
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getCost(), product.getCost()));
        recalculate();
    }

    private void recalculate() {
        totalCost = 0;
        for (CartItem item :
                items) {
            totalCost += item.getCost();
        }
    }
}
