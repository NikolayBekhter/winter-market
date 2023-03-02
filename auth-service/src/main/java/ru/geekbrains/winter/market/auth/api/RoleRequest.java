package ru.geekbrains.winter.market.auth.api;

import lombok.Data;

@Data
public class RoleRequest {
    private String username;
    private String role;
}
