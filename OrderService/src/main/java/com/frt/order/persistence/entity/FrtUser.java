package com.frt.order.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "frt_user")
public class FrtUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private FrtUserDetails frtUserDetails;

    @Column(length = 128)
    private String email;

}
