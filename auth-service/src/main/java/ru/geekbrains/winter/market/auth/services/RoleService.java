package ru.geekbrains.winter.market.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.winter.api.ResourceNotFoundException;
import ru.geekbrains.winter.market.auth.entities.Role;
import ru.geekbrains.winter.market.auth.repositories.RoleRepository;

@Component
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Роль с id: " + id + " не найдена!"));
    }
}
