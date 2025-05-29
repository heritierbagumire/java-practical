package com.app.NE.models;

import com.app.NE.audits.InitiatorAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "systemuser")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends InitiatorAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "names")
    private String names;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "national_id", unique = true)
    private String nationalId;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @OneToOne
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Meter> meters;
}
