package com.frt.authservice.model.user;

import com.frt.authservice.persistence.entity.FrtUser;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FrtUserDetailsDto {


    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private FrtUser frtUser;

}
