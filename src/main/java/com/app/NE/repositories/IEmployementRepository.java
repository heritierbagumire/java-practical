package com.app.NE.repositories;

import com.app.NE.enums.EEmployementStatus;
import com.app.NE.models.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IEmployementRepository extends JpaRepository<Employment, UUID> {
    List<Employment> findByStatus(EEmployementStatus eEmployementStatus);

    Optional<Employment> findByEmployee_Code(String code);

    Optional<Employment> findByCode(String code);

    List<Employment> findByStatusAndEmployee_Institution(EEmployementStatus status, String employeeInstitution);
}
