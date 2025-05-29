package com.app.NE.repositories;

import com.app.NE.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface INotificationRepository extends JpaRepository<Notification, UUID> {
    List<Notification> findAllByMeterNumber(int meterId);
}
