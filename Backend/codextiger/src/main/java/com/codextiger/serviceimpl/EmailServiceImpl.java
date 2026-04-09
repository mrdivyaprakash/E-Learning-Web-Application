package com.codextiger.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.codextiger.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	 private JavaMailSender mailSender;

	@Override
	public void sendOtpEmail(String email, String otp) {
		
		SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("CodexTiger Password Reset OTP");

        message.setText(
                "Your OTP for password reset is: " + otp +
                "\n\nThis OTP will expire in 5 minutes."
        );

        mailSender.send(message);
    
		
	}
}
