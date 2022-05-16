package com.example.projetsem2qrcode.service;

import com.example.projetsem2qrcode.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private UserServiceImpl userService;
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private LoginAttemptService loginAttemptService;
    private EmailService emailService;

    @Test
    void loadUserByUsername() {
    }

    @Test
    void register() {
    }

    @Test
    void addNewUser() {
    }

    @Test
    void addNewAdmin() {
    }

    @Test
    void deleteAdmin() {
    }

    @Test
    void addJo() {
    }

    @Test
    void deletejo() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void getUsers() {
    }

    @Test
    void findUserByUsername() {
    }

    @Test
    void findUserByEmail() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void resetPassword() {
    }

    @Test
    void updateProfileImage() {
    }
}