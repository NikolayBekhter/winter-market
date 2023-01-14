package ru.geekbrains.winter.market.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.winter.api.OrderDto;
import ru.geekbrains.winter.market.core.entities.Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;
    public OrderDto entityToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUsername(order.getUsername());
        orderDto.setItems(order.getItems().stream()
                .map(orderItemConverter::entityToDto)
                .collect(Collectors.toList()));
        orderDto.setTotalCost(order.getTotalCost());
        orderDto.setCreatedAt(order.getCreatedAt().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        return orderDto;
    }
}
