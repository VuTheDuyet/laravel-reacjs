package com.vutheduyet.exercise03.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vutheduyet.exercise03.entity.Notification;
import com.vutheduyet.exercise03.service.NotificationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/notifications")
public class NotificationController {

    private NotificationService notificationService;

    // Create Notification REST API
    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        Notification savedNotification = notificationService.createNotification(notification);
        return new ResponseEntity<>(savedNotification, HttpStatus.CREATED);
    }

    // Get Notification by id REST API
    // http://localhost:8080/api/notifications/{id}
    @GetMapping("{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable("id") String notificationId) {
        Notification notification = notificationService.getNotificationById(notificationId);
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }

    // Get All Notifications REST API
    // http://localhost:8080/api/notifications
    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    // Update Notification REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/notifications/{id}
    public ResponseEntity<Notification> updateNotification(@PathVariable("id") String notificationId,
                                                            @RequestBody Notification notification) {
        notification.setNotificationId(notificationId);
        Notification updatedNotification = notificationService.updateNotification(notification);
        if (updatedNotification != null) {
            return new ResponseEntity<>(updatedNotification, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Notification REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable("id") String notificationId) {
        notificationService.deleteNotification(notificationId);
        return new ResponseEntity<>("Notification successfully deleted!", HttpStatus.OK);
    }
}
