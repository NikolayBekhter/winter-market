package ru.geekbrains.winter.market.auth.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.winter.api.AuthRequest;
import ru.geekbrains.winter.api.AuthResponse;
import ru.geekbrains.winter.api.ResourceNotFoundException;
import ru.geekbrains.winter.api.UserDto;
import ru.geekbrains.winter.market.auth.converters.UserConverter;
import ru.geekbrains.winter.market.auth.entities.User;
import ru.geekbrains.winter.market.auth.UserService;
import ru.geekbrains.winter.market.auth.utils.JwtTokenUtil;

@RestController
@RequiredArgsConstructor
@Log4j2
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final UserConverter userConverter;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest request) {
        log.info("Auth request: [{}, {}]", request.getUsername(), request.getPassword());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/users")
    public UserDto saveUser(@RequestBody UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        return userConverter.entityToDto(userService.save(user));
    }

    @GetMapping("/users/{userId}")
    public UserDto findById(@PathVariable Long userId) {
        return userConverter.entityToDto(userService.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с id: " + userId + " не найден!")));
    }
}
