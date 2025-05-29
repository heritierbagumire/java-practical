package com.app.NE.repositories;

import com.app.NE.dto.responses.ApiResponse;
import com.app.NE.models.Employee;
import com.app.NE.models.PaySlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IPaySlipRepository extends JpaRepository<PaySlip, UUID> {
    List<PaySlip> findByMonthAndYear(int month, int year);

    List<PaySlip> findByEmployeeAndMonthAndYear(Employee employee, int month, int year);

    List<PaySlip> findByMonthAndYearAndEmployee_Institution(Integer month, Integer year, String employeeInstitution);
}
