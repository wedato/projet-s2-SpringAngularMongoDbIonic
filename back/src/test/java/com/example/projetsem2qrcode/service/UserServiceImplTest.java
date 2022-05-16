package com.example.projetsem2qrcode.service;

import com.example.projetsem2qrcode.exceptions.EmailExistException;
import com.example.projetsem2qrcode.exceptions.NotAnImageFileException;
import com.example.projetsem2qrcode.exceptions.UserNotFoundException;
import com.example.projetsem2qrcode.exceptions.UsernameExistException;
import com.example.projetsem2qrcode.modele.User;
import com.example.projetsem2qrcode.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private UserServiceImpl underTest;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @Mock
    private LoginAttemptService loginAttemptService;
    @Mock
    private EmailService emailService;


    @BeforeEach
    void setUp() {
        underTest = new UserServiceImpl(userRepository,passwordEncoder,loginAttemptService,emailService);
    }




    @Test
    void loadUserByUsername() {
        underTest.loadUserByUsername("mdr");
        verify(userRepository).findUserByUsername("mdr");
    }

    @Test
    void register() {
    }

    @Test
    void addNewUser() throws UserNotFoundException, EmailExistException, IOException, UsernameExistException, NotAnImageFileException {
//        String urlImageTemp = ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH + "jojo").toUriString();
//        User user = underTest.addNewUser("Jonathan","Baltaci","jojo","truc@gmail.com",ROLE_USER.name(),true,false,urlImageTemp);


    }

    @Test
    void addNewAdmin() throws UserNotFoundException, EmailExistException, UsernameExistException {
        User user = underTest.addNewAdmin();
        verify(userRepository).save(user);
        Assertions.assertNotNull(user);
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