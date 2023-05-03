package com.frt.authservice.model.user;

import com.frt.authservice.persistence.entity.FrtUser;
import com.frt.authservice.persistence.util.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FrtUserDto {


    private String email;
    private Role role;
    private Boolean isActive;

    public static FrtUserDto transformEntityToDto(FrtUser frtUser) {
        return FrtUserDto.builder()
                .email(frtUser.getEmail())
                .role(frtUser.getRole())
                .isActive(frtUser.getIsActive())
                .build();
    }

}
