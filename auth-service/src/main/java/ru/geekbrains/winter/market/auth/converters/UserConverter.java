package ru.geekbrains.winter.market.auth.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.winter.api.UserDto;
import ru.geekbrains.winter.market.auth.entities.User;

@Component
public class UserConverter {

    //из user в dto
    public UserDto entityToDto(User user){
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getFirstName());

    }
    //из dto в user
    public User dtoToEntity(UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFirstName(user.getFirstName());
        return user;
    }
}
