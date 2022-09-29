package com.example.otpservice.service;

import com.example.otpservice.config.TwilioConfig;
import com.example.otpservice.constant.MsgConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneService implements MessageService {
    private final TwilioConfig twilioConfig;

    @Autowired
    public PhoneService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    @Override
    public boolean sendMessage(String phoneNumber, String otp) {
        StringBuilder msg = new StringBuilder(MsgConstant.OTP_PHONE).append(otp);
        try {
            twilioConfig.sendMessage(phoneNumber, msg.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
