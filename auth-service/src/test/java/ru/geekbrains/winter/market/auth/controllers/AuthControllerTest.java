package ru.geekbrains.winter.market.auth.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.geekbrains.winter.market.auth.services.UserService;
import ru.geekbrains.winter.market.auth.entities.User;
import ru.geekbrains.winter.market.auth.repositories.UserRepository;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient
class AuthControllerTest {
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void findById() {
        User user = new User();
        user.setUsername("TestUser");
        user.setPassword("123");
        user.setEmail("user@mail.ru");
        user = userService.save(user);

        User userByHttp = webTestClient.get()
                .uri("/users/" + user.getId())
                .exchange()
                .expectBody(User.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertEquals(user.getId(), userByHttp.getId());
        Assertions.assertEquals(user.getUsername(), userByHttp.getUsername());
    }

    @Test
    void  findByIdNotFound() {
        //userRepository.deleteAll();

        webTestClient.get()
                .uri("/users/10")
                .exchange();
                //.expectStatus().isNotFound();
    }
}