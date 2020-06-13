package com.springboottutorial.spring_boot_tutorial.repository;

import com.springboottutorial.spring_boot_tutorial.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    int countByName(String name);

    Role findByName(String roleName);
}
