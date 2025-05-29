package com.app.NE.repositories;

import com.app.NE.enums.ERole;
import com.app.NE.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IRoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByRole(ERole eRole);
}
