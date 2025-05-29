package com.app.NE.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentRequest {
    private String employeeCode;
    private String department;
    private String position;
    private BigDecimal baseSalary;
    private LocalDate joiningDate;
} 