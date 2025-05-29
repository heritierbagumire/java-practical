package com.app.NE.models;

import com.app.NE.audits.InitiatorAudit;
import com.app.NE.enums.EDeductionName;
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
@Table(name = "deduction")
public class Deduction extends InitiatorAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private EDeductionName deductionName;

    private BigDecimal percentage;
}