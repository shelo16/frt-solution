package com.frt.authservice.persistence.repository;

import com.frt.authservice.persistence.entity.FrtUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<FrtUser, Long> {
}
