package com.medimind.medimindbackend.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendReminderEmail(
            String toEmail,
            String medicineName,
            String dosage
    ) {

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(toEmail);

        message.setSubject("Medicine Reminder");

        message.setText(
                "Time to take your medicine:\n\n"
                        + medicineName
                        + "\nDosage: "
                        + dosage
        );

        mailSender.send(message);

        System.out.println("📧 Email sent to " + toEmail);
    }

    public void sendEmail(
            String toEmail,
            String subject,
            String body
    ) {

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(toEmail);

        message.setSubject(subject);

        message.setText(body);

        mailSender.send(message);

        System.out.println("Email sent successfully");
    }
}