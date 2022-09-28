package com.example.otpservice.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.example.otpservice.constant.MsgConstant;
import com.example.otpservice.utils.OTPMailTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MailService implements MessageService {
    @Value("${email.sender}")
    private String sender;

    @Value("${email.requestTimeout}")
    private Integer requestTimeout;

    private final AmazonSimpleEmailService emailService;

    private final OTPMailTemplate otpMailTemplate;

    @Autowired
    public MailService(AmazonSimpleEmailService emailService, OTPMailTemplate otpMailTemplate) {
        this.emailService = emailService;
        this.otpMailTemplate = otpMailTemplate;
    }

    @Override
    public boolean sendMessage(String email, String msg) {
        try {
            //receive email
            Destination receiver = new Destination().withToAddresses(email);
            Content subject = new Content().withCharset(MsgConstant.CHAR_SET).withData(MsgConstant.OTP_SUBJECT);
            Content contentMail = new Content().withCharset(MsgConstant.CHAR_SET).withData(otpMailTemplate.getContent(msg));

            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(receiver)
                    .withMessage(new Message().withBody(new Body().withText(contentMail)).withSubject(subject))
                    .withSource(sender)
                    .withSdkRequestTimeout(requestTimeout);

            emailService.sendEmail(request);
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("Send email error:");
            return false;
        }
    }
}
