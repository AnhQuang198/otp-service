package com.example.otpservice.service;

import org.springframework.stereotype.Service;

@Service
public class PhoneService implements MessageService {

    @Override
    public boolean sendMessage(String phoneNumber, String msg) {
        return false;
    }
}
