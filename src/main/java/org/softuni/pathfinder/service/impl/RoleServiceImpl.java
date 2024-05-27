package org.softuni.pathfinder.service.impl;

import org.softuni.pathfinder.model.entity.Role;
import org.softuni.pathfinder.model.enums.UserRole;
import org.softuni.pathfinder.repository.RoleRepository;
import org.softuni.pathfinder.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(UserRole.valueOf(name));
    }
}
