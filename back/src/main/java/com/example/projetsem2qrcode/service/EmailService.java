package com.example.projetsem2qrcode.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendEmail(String firstName,String password, String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Wedato - New Password");
        message.setText("Hello " + firstName + "!\n\nYour new account password is: " + password + "\n\nThe Support Team");
        mailSender.send(message);
        System.out.println("Mail Sent successfully");
    }


    public void sendNewPasswordEmail(String firstName, String password, String email) {
    }
}
