package com.app.NE.repositories;

import com.app.NE.models.User;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    Optional<Object> findByPhone(@NotEmpty(message = "The phone number is required.") String phone);

    Optional<Object> findByNationalId(@NotEmpty(message = "The national id is required.") String nationalId);

    @Query("SELECT u FROM User u JOIN u.meters m WHERE m.meterNumber = :meterNumber")
    Optional<User> findByMeterNumber(@Param("meterNumber") int meterNumber);
}
