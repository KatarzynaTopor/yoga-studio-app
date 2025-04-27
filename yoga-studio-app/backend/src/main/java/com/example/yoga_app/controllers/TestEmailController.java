package com.example.yoga_app.controllers;

import com.example.yoga_app.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestEmailController {

    private final EmailService emailService;

    @GetMapping("/email")
    public String sendTestEmail() {
        emailService.sendEmail(
                "kasiatopor6@gmail.com",
                "Testowy Email",
                "To jest testowa wiadomość wysłana z backendu :)"
        );
        return "Testowy email został wysłany!";
    }
}
