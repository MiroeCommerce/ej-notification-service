package com.ecommerce.notification_service.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailNotificationListener {

    private final JavaMailSender mailSender;

    public EmailNotificationListener(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @EventListener
    public void handleEmailNotificationEvent(EmailNotificationEvent event) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(event.getRecipient());
        message.setSubject(event.getSubject());
        message.setText(event.getBody());
        mailSender.send(message);

        log.info("Email sent via local SMTP server to: {} ", event.getRecipient());
    }
}