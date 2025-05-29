package com.app.NE.dto.requests;

import com.app.NE.enums.EEmployementStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeementDTO {
    private String department;
    private String position;
    private BigDecimal baseSalary;
    private EEmployementStatus status;
    private LocalDate joinDate;
    private String employeeCode;
}
