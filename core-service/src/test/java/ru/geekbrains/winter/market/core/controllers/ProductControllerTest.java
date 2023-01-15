package ru.geekbrains.winter.market.core.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.geekbrains.winter.api.ProductDto;
import ru.geekbrains.winter.market.core.converters.ProductConverter;
import ru.geekbrains.winter.market.core.entities.Product;
import ru.geekbrains.winter.market.core.repositories.CategoryRepository;
import ru.geekbrains.winter.market.core.repositories.ProductRepository;
import ru.geekbrains.winter.market.core.services.ProductService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ProductControllerTest {
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductConverter productConverter;

    @Test
    void createNewProduct() {
        Product product = new Product();
        product.setTitle("TestProduct");
        product.setCost(new BigDecimal("10.0"));
        product.setCategory(categoryRepository.findById(1L).orElse(null));
        product = productRepository.save(product);

        ProductDto productDto = productConverter.entityToDto(product);

        ProductDto productByHttp = webTestClient.post()
                .uri("/api/v1/products")
                .bodyValue(productDto)
                .exchange()
                .expectBody(ProductDto.class)
                .returnResult()
                .getResponseBody();

        //Assertions.assertEquals(product.getId(), productByHttp.getId());
        Assertions.assertEquals(product.getTitle(), productByHttp.getTitle());
        Assertions.assertEquals(product.getCategory().getTitle(), productByHttp.getCategoryTitle());
        Assertions.assertEquals(product.getCost(), productByHttp.getCost());
    }

    @Test
    void getProduct() {
        Product product = productService.findProductById(1L).orElse(null);

        ProductDto productByHttp = webTestClient.get()
                .uri("/api/v1/products/" + product.getId())
                .exchange()
                .expectBody(ProductDto.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertNotNull(productByHttp);
        Assertions.assertNotNull(productByHttp.getTitle());

        Assertions.assertEquals(product.getId(), productByHttp.getId());
        Assertions.assertEquals(product.getCost(), productByHttp.getCost());
        Assertions.assertEquals(product.getCategory().getTitle(), productByHttp.getCategoryTitle());
        Assertions.assertEquals(product.getTitle(), productByHttp.getTitle());
    }

    @Test
    void testFindByIdNotFound() {
        //productRepository.deleteAll();

        webTestClient.get()
                .uri("/api/v1/products/4")
                .exchange()
                .expectStatus().isNotFound();
    }

}