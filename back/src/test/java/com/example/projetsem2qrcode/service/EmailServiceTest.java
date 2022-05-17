package com.example.projetsem2qrcode.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;

@ContextConfiguration(classes = {EmailService.class})
@ExtendWith(SpringExtension.class)
class EmailServiceTest {

    @MockBean
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailService emailService;


    @BeforeEach
    void setUp() {
        emailService = new EmailService(javaMailSender);
    }

    /**
     * Method under test: {@link EmailService#sendEmail(String, String, String)}
     */
    @Test
    void testSendEmailOk() throws MailException {
        //when
        doNothing().when(this.javaMailSender).send((org.springframework.mail.SimpleMailMessage) any());
        this.emailService.sendEmail("louisss", "sssiuol", "louis.todo@example.org");
    }

}