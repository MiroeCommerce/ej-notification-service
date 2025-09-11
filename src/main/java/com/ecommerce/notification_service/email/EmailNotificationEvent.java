package com.ecommerce.notification_service.email;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EmailNotificationEvent extends ApplicationEvent {

    private final String recipient;
    private final String subject;
    private final String body;

    public EmailNotificationEvent(Object source, String recipient, String subject, String body) {
        super(source);
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
    }

}
