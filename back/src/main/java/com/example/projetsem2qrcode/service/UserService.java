package com.example.projetsem2qrcode.service;

import com.example.projetsem2qrcode.exceptions.*;
import com.example.projetsem2qrcode.modele.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, EmailExistException, UsernameExistException, NumEtudiantDejaPresentException;
    List<User> getUsers();
    User findUserByUsername(String username);
    User findUserByEmail(String email);

    User addNewUser(String firstName, String lastName, String username, String email, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException, NotAnImageFileException;

    User updateUser(String currentUsername,String newFirstname, String newLastName, String newUsername, String newEemail, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException, NotAnImageFileException;

    void deleteUser(String username) throws UserNotFoundException, IOException;

    void resetPassword(String email) throws EmailNotFoundException;



    User updateProfileImage(String username, MultipartFile profileImage) throws UserNotFoundException, EmailExistException, UsernameExistException, IOException, NotAnImageFileException;

}
