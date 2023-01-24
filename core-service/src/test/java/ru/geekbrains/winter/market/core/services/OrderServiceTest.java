package ru.geekbrains.winter.market.core.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.geekbrains.winter.api.CartDto;
import ru.geekbrains.winter.api.CartItemDto;
import ru.geekbrains.winter.api.UserDto;
import ru.geekbrains.winter.market.core.entities.Order;
import ru.geekbrains.winter.market.core.entities.Product;
import ru.geekbrains.winter.market.core.integrations.CartServiceIntegration;
import ru.geekbrains.winter.market.core.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class OrderServiceTest {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    ProductService productService;
    @MockBean
    CartServiceIntegration cartServiceIntegration;

    @Test
    void createOrder() {
        UserDto user = new UserDto();
        user.setUsername("TestUser");

        Product product = productService.findProductById(1L).orElse(null);
        List<CartItemDto> items = new ArrayList<>();

        {
            CartItemDto cartItemDto = new CartItemDto();
            cartItemDto.setProductTitle(product.getTitle());
            cartItemDto.setProductId(product.getId());
            cartItemDto.setCost(product.getCost());
            cartItemDto.setCostPerProduct(product.getCost());

            items.add(cartItemDto);
        }

        CartDto cartDto = new CartDto();
        cartDto.setTotalCost(product.getCost());
        cartDto.setItems(items);

        Mockito.when(cartServiceIntegration.getCurrentCart(user.getUsername())).thenReturn(cartDto);

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