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
import ru.geekbrains.winter.api.*;
import ru.geekbrains.winter.market.auth.converters.UserConverter;
import ru.geekbrains.winter.market.auth.entities.User;
import ru.geekbrains.winter.market.auth.services.UserService;
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

    @PostMapping("/registration")
    public ResponseEntity<?> createUser(@RequestBody RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с таким именем уже существует"), HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setFirstName(registrationUserDto.getFirstName());
        user.setUsername(registrationUserDto.getUsername());
        user.setPassword(registrationUserDto.getPassword());
        return ResponseEntity.ok(userConverter.entityToDto(userService.save(user)));
    }

    @GetMapping("/users/{userId}")
    public UserDto findById(@PathVariable Long userId) {
        return userConverter.entityToDto(userService.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с id: " + userId + " не найден!")));
    }
}
