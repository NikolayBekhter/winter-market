package ru.geekbrains.winter.market.auth.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.geekbrains.winter.market.auth.entities.User;
import ru.geekbrains.winter.market.auth.repositories.UserRepository;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    void findByUsername() {
    }

    @Test
    void save() {
        User user = new User();
        user.setUsername("TestUser");
        user.setPassword("123");
        user.setEmail("user@mail.ru");
        User savedUser = userService.save(user);
        Assertions.assertNotNull(savedUser);
        Assertions.assertNotNull(savedUser.getId());
        Assertions.assertEquals("TestUser", savedUser.getUsername());

        User createdUser = userRepository.findById(savedUser.getId()).orElse(null);
        Assertions.assertEquals("TestUser", createdUser.getUsername());
    }
}