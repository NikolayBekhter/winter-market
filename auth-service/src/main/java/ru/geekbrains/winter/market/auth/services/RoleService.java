package ru.geekbrains.winter.market.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.winter.market.auth.api.ResourceNotFoundException;
import ru.geekbrains.winter.market.auth.entities.Role;
import ru.geekbrains.winter.market.auth.repositories.RoleRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name).orElseThrow(() -> new ResourceNotFoundException("Роль с id: " + name + " не найдена!"));
    }

}
