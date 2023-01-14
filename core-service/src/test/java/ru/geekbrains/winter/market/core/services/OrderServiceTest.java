package ru.geekbrains.winter.market.core.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.geekbrains.winter.api.UserDto;
import ru.geekbrains.winter.market.core.entities.Order;
import ru.geekbrains.winter.market.core.repositories.OrderRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void createOrder() {
        UserDto user = new UserDto();
        user.setUsername("TestUser");

        Order createdOrder = orderService.createOrder(user.getUsername());
        Assertions.assertNotNull(createdOrder);
        Assertions.assertNotNull(createdOrder.getId());
        Assertions.assertEquals(user.getUsername(), createdOrder.getUsername());

        Order savedOrder = orderRepository.findById(createdOrder.getId()).orElse(null);
        Assertions.assertEquals(createdOrder.getId(), savedOrder.getId());
        Assertions.assertEquals(createdOrder.getUsername(), savedOrder.getUsername());
    }

    @Test
    void getOrder() {
    }
}