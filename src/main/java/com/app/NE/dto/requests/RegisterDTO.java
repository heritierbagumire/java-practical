package com.app.NE.dto.requests;

import com.app.NE.enums.ERole;
import com.app.NE.security.validation.annotations.ValidNationalId;
import com.app.NE.security.validation.annotations.ValidPassword;
import com.app.NE.security.validation.annotations.ValidPhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    @NotEmpty(message = "The last name is required.")
    private String lastName;

    @NotEmpty(message = "The first name is required.")
    private String firstName;

    @NotEmpty(message = "The email is required.")
    @Email(message = "Invalid email provided")
    private String email;

    @NotEmpty(message = "The phone number is required.")
    @ValidPhoneNumber
    private String phone;

    @NotEmpty(message = "The password is required.")
    @ValidPassword
    private String password;

    private ERole role;
    private String adminCreationKey;
}
