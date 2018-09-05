package ru.dorofeev22.caregiving.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dorofeev22.caregiving.dtos.EmailDto;
import ru.dorofeev22.caregiving.services.EmailService;

@RestController
@RequestMapping(value = "/email", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class EmailController {

    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PutMapping("/send")
    public void sendMessage(@RequestBody EmailDto emailDto) {
        emailService.sendEmail(emailDto);
    }
}
