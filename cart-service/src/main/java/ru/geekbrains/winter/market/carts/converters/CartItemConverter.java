package ru.geekbrains.winter.market.carts.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.winter.market.carts.api.CartItemDto;
import ru.geekbrains.winter.market.carts.model.CartItem;

@Component
public class CartItemConverter {
    public CartItemDto entityToDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setCost(cartItem.getCost());
        cartItemDto.setProductTitle(cartItem.getProductTitle());
        cartItemDto.setCostPerProduct(cartItem.getCostPerProduct());
        cartItemDto.setQuantity(cartItem.getQuantity());
        return cartItemDto;
    }
}
