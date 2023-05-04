package com.frt.authservice.model.user;

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

    @Nullable
    @Email
    private String email;

    @Nullable
    private String password;

}
