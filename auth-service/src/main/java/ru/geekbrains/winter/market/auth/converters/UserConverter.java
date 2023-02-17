package ru.geekbrains.winter.market.auth.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.winter.market.auth.api.UserDto;
import ru.geekbrains.winter.market.auth.entities.User;

@Component
public class UserConverter {

    //из user в dto
    public UserDto entityToDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .isActive(user.isActive())
                .build();
    }
    //из dto в user
    public User dtoToEntity(UserDto userDto){
        return User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .firstName(userDto.getFirstName())
                .build();
    }
}
