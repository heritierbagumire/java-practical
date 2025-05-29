package com.app.NE.models;

import com.app.NE.audits.InitiatorAudit;
import com.app.NE.enums.EEmployementStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employment")
public class Employment extends InitiatorAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String code;

    @OneToOne
    @JoinColumn(name = "employee_code")
    private Employee employee;

    private String department;
    private String position;
    private BigDecimal baseSalary;

    @Enumerated(EnumType.STRING)
    private EEmployementStatus status;

    private LocalDate joiningDate;
}