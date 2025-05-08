package com.example.yoga_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailQueueListener {

    private final EmailService emailService;

    @RabbitListener(queues = "email-queue")
    public void handleEmailQueue(String email) {
        System.out.println(" Received email task from queue: " + email);

        emailService.sendEmail(
                email,
                "Welcome to Swallow’s Nest Yoga",
                "<h3>Thank you for registering!</h3><p>We're excited to have you with us </p>"
        );
    }
}
