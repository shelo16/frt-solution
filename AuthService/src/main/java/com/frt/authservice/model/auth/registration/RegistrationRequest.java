package com.frt.authservice.model.auth.registration;

import com.frt.authservice.exception.model.password.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;

    @ValidPassword
    private String password;

}
