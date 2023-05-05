package com.frt.notification.persistence.repository;

import com.frt.notification.persistence.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
