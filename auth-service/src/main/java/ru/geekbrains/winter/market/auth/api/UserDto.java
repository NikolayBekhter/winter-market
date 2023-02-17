package ru.geekbrains.winter.market.auth.api;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private boolean isActive;
}
