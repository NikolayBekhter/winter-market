package ru.geekbrains.winter.market.core.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.winter.market.core.api.OrderDto;
import ru.geekbrains.winter.market.core.converters.OrderConverter;
import ru.geekbrains.winter.market.core.services.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@Log4j2
public class OrderController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username) {
        orderService.createOrder(username);
    }

    @GetMapping
    public List<OrderDto> getOrders(@RequestHeader String username) {
        log.info(orderService.getOrder(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList()));
        return orderService.getOrder(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrderById(orderId);
    }
}
