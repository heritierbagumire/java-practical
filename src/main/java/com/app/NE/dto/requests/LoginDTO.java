package com.app.NE.dto.requests;

import com.app.NE.security.validation.annotations.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @NotEmpty(message = "The email is required")
    @Email(message = "Invalid email provided")
    private String email;

    @NotEmpty(message = "The password is required")
    @ValidPassword
    private String password;
}
