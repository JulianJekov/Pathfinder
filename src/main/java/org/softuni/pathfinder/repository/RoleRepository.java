package org.softuni.pathfinder.repository;

import org.softuni.pathfinder.model.entity.Role;
import org.softuni.pathfinder.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(UserRole role);
}
