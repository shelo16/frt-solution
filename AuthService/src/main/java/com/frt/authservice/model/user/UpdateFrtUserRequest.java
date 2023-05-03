package com.frt.authservice.model.user;

import com.frt.authservice.exception.model.password.ValidPassword;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFrtUserRequest {

    @Email
    @Nullable
    private String email;

    @ValidPassword
    @Nullable
    private String password;

}
