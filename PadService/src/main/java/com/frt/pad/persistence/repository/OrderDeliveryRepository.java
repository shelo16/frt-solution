package com.frt.pad.persistence.repository;

import com.frt.pad.persistence.entity.OrderDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OrderDeliveryRepository extends JpaRepository<OrderDelivery, Long> {

    @Query("SELECT od FROM OrderDelivery od WHERE od.order.clientId = :clientId")
    List<OrderDelivery> findByClientId(@Param("clientId") Long clientId);

}
