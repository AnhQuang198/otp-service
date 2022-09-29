package com.example.otpservice.config;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {
    @Value("${twilio.accountSId}")
    private String accountSId;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.phoneNumber}")
    private String srcPhoneNumber;

    public void sendMessage(String phoneNumber, String msg) throws Exception {
        StringBuilder targetNumber = new StringBuilder("+84").append(phoneNumber.substring(1));
        Twilio.init(accountSId, authToken);
        Message.creator(
                new PhoneNumber(targetNumber.toString()),
                new PhoneNumber(srcPhoneNumber),
                msg).create();
    }
}
