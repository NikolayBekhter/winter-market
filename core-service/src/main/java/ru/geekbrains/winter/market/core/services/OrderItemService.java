package ru.geekbrains.winter.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.winter.market.core.entities.OrderItem;
import ru.geekbrains.winter.market.core.repositories.OrderItemRepository;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
