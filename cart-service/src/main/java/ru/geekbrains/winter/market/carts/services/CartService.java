package ru.geekbrains.winter.market.carts.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.geekbrains.winter.api.ProductDto;
import ru.geekbrains.winter.market.carts.integrations.ProductServiceIntegration;
import ru.geekbrains.winter.market.carts.model.Cart;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;

    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;
    private Map<String, Cart> carts;

    @PostConstruct
    public void init() {
        carts = new HashMap<>();
    }

    public Cart getCurrentCart(String uuid) {
        String targetUuid = cartPrefix + uuid;
        if (!carts.containsKey(targetUuid)) {
            carts.put(targetUuid, new Cart());
        }
        return carts.get(targetUuid);
    }

    public void add(String uuid, Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        getCurrentCart(uuid).add(product);
    }

    public void remove(String uuid, Long productId) {
        getCurrentCart(uuid).remove(productId);
    }

    public void clear(String uuid) {
        getCurrentCart(uuid).clear();
    }

    public void changeQuantity(String uuid, Long productId, Integer delta) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        getCurrentCart(uuid).changeQuantity(product, delta);
    }
}
