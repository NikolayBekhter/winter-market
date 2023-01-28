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
        totalCost = BigDecimal.ZERO;
    }

    private void recalculate() {
        totalCost = BigDecimal.ZERO;
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

    public void addItem(CartItem cartItem) {
        ProductDto productDto = new ProductDto();
        productDto.setId(cartItem.getProductId());
        productDto.setCost(cartItem.getCost());
        productDto.setTitle(cartItem.getProductTitle());
        add(productDto);
        changeQuantity(productDto, cartItem.getQuantity() - 1);
    }
}
