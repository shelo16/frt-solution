package com.frt.authservice.persistence.repository;

import com.frt.authservice.persistence.entity.FrtUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FrtUserRepository extends JpaRepository<FrtUser, Long> {

    Optional<FrtUser> findByEmail(String email);

}
