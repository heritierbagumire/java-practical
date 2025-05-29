package com.app.NE.models;

import com.app.NE.audits.InitiatorAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "messages")
public class Message extends InitiatorAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "employee_code")
    private Employee employee;

    @Column(columnDefinition = "TEXT")
    private String message;

    private Integer month;
    private Integer year;
    private LocalDateTime sentAt;

    private boolean sent;
}