package com.example.otpservice.service;

import com.example.otpservice.enums.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageFactory {
    private final MailService mailService;
    private final PhoneService phoneService;

    @Autowired
    public MessageFactory(MailService mailService, PhoneService phoneService) {
        this.mailService = mailService;
        this.phoneService = phoneService;
    }

    public boolean send(String type, String receiver, String msg) {
        switch (MessageType.valueOf(type)) {
            case MAIL:
                return mailService.sendMessage(receiver, msg);
            case PHONE:
                return phoneService.sendMessage(receiver, msg);
            default:
                throw new IllegalArgumentException("Undefine type: " + type);
        }
    }
}
