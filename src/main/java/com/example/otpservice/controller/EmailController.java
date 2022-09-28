package com.example.otpservice.controller;

import com.example.otpservice.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/send")
public class EmailController {

    @Autowired
    private MailService mailService;

    @GetMapping
    public ResponseEntity<?> sendOTPEmail(@RequestParam String email, @RequestParam String otp) {
        this.mailService.sendMessage(email, otp);
        return ResponseEntity.ok("ok");
    }
}
