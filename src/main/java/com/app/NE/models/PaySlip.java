package com.app.NE.models;

import com.app.NE.audits.InitiatorAudit;
import com.app.NE.enums.EPaySlipStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pay_slips")
public class PaySlip extends InitiatorAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "employee_code")
    private Employee employee;

    private BigDecimal houseAmount;
    private BigDecimal transportAmount;
    private BigDecimal taxAmount;
    private BigDecimal pensionAmount;
    private BigDecimal medicalAmount;
    private BigDecimal otherAmount;

    private BigDecimal grossSalary;
    private BigDecimal netSalary;
    private Integer month;
    private Integer year;

    @Enumerated(EnumType.STRING)
    private EPaySlipStatus status;
}
