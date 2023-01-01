package ru.geekbrains.winter.market.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.winter.api.CartDto;
import ru.geekbrains.winter.market.carts.converters.CartConverter;
import ru.geekbrains.winter.market.carts.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.add(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clear();
    }

    @GetMapping("/remove/{id}")
    public void removeFromCart(@PathVariable Long id) {
        cartService.remove(id);
    }

    @GetMapping
    public CartDto getCurrentCart() {
        return cartConverter.entityToDto(cartService.getCurrentCart());
    }

    @GetMapping("/change_quantity")
    public void changeQuantity(@RequestParam Long productId, @RequestParam Integer delta) {
        cartService.changeQuantity(productId, delta);
    }

}
