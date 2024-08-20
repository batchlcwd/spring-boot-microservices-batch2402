package com.elearn.app.repositories;

import com.elearn.app.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, String>
{
    Optional<Role> findByRoleName(String roleName);
}
