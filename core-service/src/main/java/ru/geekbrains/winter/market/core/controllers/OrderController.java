package ru.geekbrains.winter.market.core.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.winter.market.core.entities.Order;
import ru.geekbrains.winter.market.core.services.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@Log4j2
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username) {
        log.info(username);
        orderService.createOrder(username);
        //TODO формируется заказ из пустой корзины...
    }

    @GetMapping
    public List<Order> getOrder(@RequestHeader String username) {
        return orderService.getOrder(username);
    }
}
