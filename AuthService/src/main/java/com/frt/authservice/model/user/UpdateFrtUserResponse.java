package com.frt.authservice.model.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateFrtUserResponse {

    private String message;
    private Long frtUserId;

}
