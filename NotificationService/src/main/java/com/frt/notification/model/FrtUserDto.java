package com.frt.notification.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FrtUserDto {

    private Long userId;
    private String address;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String email;

}
