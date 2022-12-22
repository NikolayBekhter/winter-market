package ru.geekbrains.spring.winter.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.winter.market.entities.Order;
import ru.geekbrains.spring.winter.market.entities.OrderItem;
import ru.geekbrains.spring.winter.market.entities.User;
import ru.geekbrains.spring.winter.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.winter.market.model.Cart;
import ru.geekbrains.spring.winter.market.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final CartService cartService;
    private final OrderItemService orderItemService;
    private final OrderRepository orderRepository;

    @Transactional
    public void createOrder(User user) {
        Cart cart = cartService.getCurrentCart();

        Order order = new Order();
        order.setUser(user);
        order.setTotalCost(cart.getTotalCost());
        orderRepository.save(order);

        List<OrderItem> orderItems = cart.getItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(productService.findProductById(cartItem.getProductId())
                            .orElseThrow(() -> new ResourceNotFoundException("Не удается добавить продукт с id: " + cartItem.getProductId()
                            + " в заказ. Продукт не найден.")));
                    orderItem.setOrder(order);
                    orderItem.setCost(cartItem.getCost());
                    orderItem.setCostPerProduct(cartItem.getCostPerProduct());
                    orderItem.setQuantity(cartItem.getQuantity());
                    return orderItemService.save(orderItem);
                }).collect(Collectors.toList());
    }

    public List<Order> getOrder(User user) {
        return orderRepository.findAllById(user.getId());
    }
}
