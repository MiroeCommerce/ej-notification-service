package com.ecommerce.notification_service.service;

import com.ecommerce.notification_service.email.EmailNotificationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final ApplicationEventPublisher eventPublisher;

    public EmailService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void sendEmail(String subject, String userEmail, String body) {

        EmailNotificationEvent event = new EmailNotificationEvent(this, userEmail, subject, body);
        eventPublisher.publishEvent(event);
    }
}
