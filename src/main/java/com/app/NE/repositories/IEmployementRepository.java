package com.app.NE.repositories;

import com.app.NE.models.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IEmployementRepository extends JpaRepository<Employment, UUID> {
}
