package com.haddad.blogin.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.Random;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailsender;

    @Value("${spring.mail.username}")
    private String fromEmail;
    public String generateVerificationCode() {
        // 1. Use a cryptographically strong random number generator.
        SecureRandom secureRandom = new SecureRandom();

        // 2. Generate a number between 0 and 999999.
        // nextInt(bound) generates a value from 0 (inclusive) to bound (exclusive),
        // so nextInt(1_000_000) will generate numbers from 0 to 999999.
        int number = secureRandom.nextInt(1_000_000);

        // 3. Format the number as a 6-digit string.
        // This is crucial for handling numbers less than 100000 (e.g., 123 -> "000123").
        return new DecimalFormat("000000").format(number);
    }

    public void sendEmail(String toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailsender.send(message);

        System.out.println("Email sent successfully!");
    }
}
