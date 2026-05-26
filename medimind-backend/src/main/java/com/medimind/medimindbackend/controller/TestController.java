package com.medimind.medimindbackend.controller;

import com.medimind.medimindbackend.service.EmailService;
import com.medimind.medimindbackend.repository.TestRepository;
import com.medimind.medimindbackend.model.TestModel;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/test")
@CrossOrigin("*")
public class TestController {

    private final TestRepository testRepository;
    private final EmailService emailService;

    public TestController(TestRepository testRepository, EmailService emailService) {
        this.testRepository = testRepository;
        this.emailService = emailService;
    }

    @GetMapping
    public String test() {
        return "Backend Working Successfully";
    }

    @PostMapping
    public TestModel save(@RequestBody TestModel model) {
        return testRepository.save(model);
    }

    @GetMapping("/protected")
    public String protectedRoute() {
        return "Protected API Accessed";
    }

    @GetMapping("/send-email")
    public String sendEmail() {

        emailService.sendReminderEmail(
                "abhijaiswal0682@gmail.com",
                "Paracetamol",
                "500mg"
        );

        return "Email Sent Successfully";
    }
}