package com.example.yoga_app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import static com.example.yoga_app.config.RabbitMQConfig.EMAIL_QUEUE;

@Service
@RequiredArgsConstructor
public class EmailQueueSender {

    private final AmqpTemplate rabbitTemplate;

    public void sendEmailRequest(String email) {
        rabbitTemplate.convertAndSend(EMAIL_QUEUE, email);
        System.out.println(" Sent email task to queue: " + email);
    }
}
