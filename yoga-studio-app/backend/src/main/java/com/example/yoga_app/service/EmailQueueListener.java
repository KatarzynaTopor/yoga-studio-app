package com.example.yoga_app.service;

import com.example.yoga_app.dto.EmailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailQueueListener {

    private final EmailService emailService;

    @RabbitListener(queues = "email-queue")
    public void handleEmailQueue(EmailMessage message) {
        System.out.println(" Received message: " + message);

        emailService.sendEmail(
                message.to(),
                message.subject(),
                message.content()
        );
    }
}
