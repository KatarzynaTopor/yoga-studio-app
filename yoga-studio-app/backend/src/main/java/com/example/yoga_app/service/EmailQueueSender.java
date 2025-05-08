package com.example.yoga_app.service;

import com.example.yoga_app.dto.EmailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import static com.example.yoga_app.config.RabbitMQConfig.EMAIL_QUEUE;

@Service
@RequiredArgsConstructor
public class EmailQueueSender {

    private final AmqpTemplate rabbitTemplate;

    public void sendEmail(EmailMessage message) {
        rabbitTemplate.convertAndSend(EMAIL_QUEUE, message);
        System.out.println(" Sent email to queue: " + message.to());
    }
}
