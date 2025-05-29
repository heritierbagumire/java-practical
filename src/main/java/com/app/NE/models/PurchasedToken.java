package com.app.NE.models;

import com.app.NE.audits.InitiatorAudit;
import com.app.NE.enums.ETokenStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "purchased_token")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PurchasedToken extends InitiatorAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "meter_number", length = 6)
    private int meterNumber;

    @Column(name = "token")
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "token_status")
    private ETokenStatus status;

    @Column(name = "token_value_days", length = 11)
    private Integer tokenValueDays;

    @Column(name = "purchased_date")
    private LocalDateTime purchasedDate;

    @Column(name = "amount", length = 11)
    private Integer amount;
}
