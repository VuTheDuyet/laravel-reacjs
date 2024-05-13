package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.Notification;
import com.vutheduyet.exercise03.repository.NotificationRepository;
import com.vutheduyet.exercise03.service.NotificationService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private NotificationRepository notificationRepository;

    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification getNotificationById(String notificationId) {
        Optional<Notification> optionalNotification = notificationRepository.findById(notificationId);
        return optionalNotification.orElse(null);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification updateNotification(Notification notification) {
        Optional<Notification> optionalExistingNotification = notificationRepository.findById(notification.getNotificationId());
        if (optionalExistingNotification.isPresent()) {
            Notification existingNotification = optionalExistingNotification.get();
            existingNotification.setTitle(notification.getTitle());
            existingNotification.setContent(notification.getContent());
            existingNotification.setSeen(notification.getSeen());
            existingNotification.setNotificationExpiryDate(notification.getNotificationExpiryDate());
    
            Notification updatedNotification = notificationRepository.save(existingNotification);
            return updatedNotification;
        }
    
        return null;
    }

    @Override
    public void deleteNotification(String notificationId) {
        notificationRepository.deleteById(notificationId);
    }
}
