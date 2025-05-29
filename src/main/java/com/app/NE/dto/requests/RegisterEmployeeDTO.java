package com.app.NE.dto.requests;

import com.app.NE.enums.EEmployeeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterEmployeeDTO extends RegisterDTO {
    private LocalDate dateOfBirth;
    private EEmployeeStatus status;
}
