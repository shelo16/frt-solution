package com.frt.authservice.model.auth.registration;

import com.frt.authservice.exception.model.password.ValidPassword;
import com.frt.authservice.exception.model.phone.ValidPhone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationRequest {

    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;

    @ValidPassword
    private String password;

    @NotEmpty(message = "Please provide your name")
    private String firstName;

    @NotEmpty(message = "Please provide your last name")
    private String lastName;

    @ValidPhone
    private String phoneNumber;

    @NotEmpty(message = "Please provide your address")
    private String address;

}
