package ru.geekbrains.winter.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.geekbrains.winter.api.CartDto;
import ru.geekbrains.winter.api.ResourceNotFoundException;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    /*public CartDto getProductById(Long id) {
        return cartServiceWebClient.get()
                .uri("api/v1/cart/" + id)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Товар не найден в продуктовом MS"))
                        )
                .bodyToMono(CartDto.class)
                .block();
    }*/

    public CartDto getCurrentCart() {
        return cartServiceWebClient.get()
                .uri("api/v1/cart")
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Корзина не найдена в продуктовом MS"))
                )
                .bodyToMono(CartDto.class)
                .block();
    }
}
