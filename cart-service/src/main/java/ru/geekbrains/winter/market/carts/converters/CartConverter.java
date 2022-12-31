package ru.geekbrains.winter.market.carts.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.winter.api.CartDto;
import ru.geekbrains.winter.api.CartItemDto;
import ru.geekbrains.winter.market.carts.model.Cart;
import ru.geekbrains.winter.market.carts.model.CartItem;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {
    private final CartItemConverter cartItemConverter;
    public CartDto entityToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setTotalCost(cart.getTotalCost());
        cartDto.setItems(cart.getItems().stream()
                .map(cartItemConverter::entityToDto)
                .collect(Collectors.toList()));
        return cartDto;
    }
}
