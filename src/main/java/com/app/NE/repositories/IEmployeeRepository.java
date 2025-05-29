package com.app.NE.repositories;

import com.app.NE.models.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, UUID> {
    Optional<Employee> findByCode(String code);

    boolean existsByEmail(@NotEmpty(message = "The email is required.") @Email(message = "Invalid email provided") String email);

    List<Employee> findAllByInstitution(String institution);
}
