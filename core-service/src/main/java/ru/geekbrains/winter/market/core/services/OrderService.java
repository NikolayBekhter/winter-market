package ru.geekbrains.winter.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.winter.market.core.api.CartDto;
import ru.geekbrains.winter.market.core.api.ResourceNotFoundException;
import ru.geekbrains.winter.market.core.entities.Order;
import ru.geekbrains.winter.market.core.entities.OrderItem;
import ru.geekbrains.winter.market.core.integrations.CartServiceIntegration;
import ru.geekbrains.winter.market.core.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final OrderItemService orderItemService;
    private final OrderRepository orderRepository;
    private  final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public Order createOrder(String username) {
        CartDto cartDto = cartServiceIntegration.getCurrentCart(username);

        Order order = new Order();
        order.setUsername(username);
        order.setTotalCost(cartDto.getTotalCost());
        orderRepository.save(order);

        List<OrderItem> orderItems = cartDto.getItems().stream()
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
        return order;
    }

    public List<Order> getOrder(String username) {
        return orderRepository.findAllByUsername(username);
    }

    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
