package ru.geekbrains.winter.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.winter.api.CartDto;
import ru.geekbrains.winter.api.ResourceNotFoundException;
import ru.geekbrains.winter.market.core.entities.Order;
import ru.geekbrains.winter.market.core.entities.OrderItem;
import ru.geekbrains.winter.market.core.entities.User;
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
    public void createOrder(User user) {
        CartDto cartDto = cartServiceIntegration.getCurrentCart();

        Order order = new Order();
        order.setUser(user);
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
    }
    //TODO сделать мапинг для Order и OrderItem
    public List<Order> getOrder(User user) {
        return orderRepository.findAllById(user.getId());
    }
}
