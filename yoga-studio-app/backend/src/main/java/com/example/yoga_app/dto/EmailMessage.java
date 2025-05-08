package com.example.yoga_app.dto;

import java.io.Serializable;

public record EmailMessage(
        String to,
        String subject,
        String content
) implements Serializable {}
