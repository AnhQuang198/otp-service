package com.example.otpservice.consumer;

import com.example.otpservice.service.MessageFactory;
import demo.account.authen.OtpMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class OtpConsumer {
    public static final String OTP_TOPIC = "demo.otp";

    @Autowired
    private MessageFactory messageFactory;

    @KafkaListener(topics = {OTP_TOPIC})
    public void readMessage(OtpMessage otpMessage) {
        log.info("Message {}, {}", otpMessage.getOtp(), otpMessage.getReceiver());
        boolean status = this.messageFactory.send(otpMessage.getType(), otpMessage.getReceiver(), otpMessage.getOtp());
        if (!status) {
            log.error("Error send {} message", otpMessage.getOtp());
        }
    }
}
