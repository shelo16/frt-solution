package com.frt.order.persistence.repository;

import com.frt.order.model.user.FrtUserDto;
import com.frt.order.persistence.entity.FrtUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FrtUserDetailsRepository extends JpaRepository<FrtUserDetails, Long> {

    @Query("SELECT NEW com.frt.order.model.user.FrtUserDto(u.frtUser.userId, u.address, u.phoneNumber, u.firstName, u.lastName, u.frtUser.email) FROM FrtUserDetails u " +
            "WHERE u.frtUser.userId = :userId")
    Optional<FrtUserDto> getUserById(@Param("userId") Long userId);

    @Query("SELECT NEW com.frt.order.model.user.FrtUserDto(u.frtUser.userId, u.address, u.phoneNumber, u.firstName, u.lastName, u.frtUser.email) FROM FrtUserDetails u " +
            "WHERE u.frtUser.userId IN (:userIdList)")
    Optional<List<FrtUserDto>> getUsersById(@Param("userIdList") List<Long> userIdList);

}
