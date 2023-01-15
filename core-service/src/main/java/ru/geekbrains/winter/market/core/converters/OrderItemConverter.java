package ru.geekbrains.winter.market.core.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.winter.api.OrderItemDto;
import ru.geekbrains.winter.market.core.entities.OrderItem;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setProductTitle(orderItem.getProduct().getTitle());
        orderItemDto.setOrderId(orderItem.getOrder().getId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setCostPerProduct(orderItem.getCostPerProduct());
        orderItemDto.setCost(orderItem.getCost());
        return orderItemDto;
    }
}
