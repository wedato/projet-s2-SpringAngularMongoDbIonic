package com.example.projetsem2qrcode.controlleradmin;


import com.example.projetsem2qrcode.exceptions.EmailExistException;
import com.example.projetsem2qrcode.exceptions.UserNotFoundException;
import com.example.projetsem2qrcode.exceptions.UsernameExistException;
import com.example.projetsem2qrcode.modele.User;
import com.example.projetsem2qrcode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

// format date indice?
//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss" ,timezone = "Europe/Paris")

@RestController
@RequestMapping(value="/user")
public class User2Controller  {

    private UserService userService;

    @Autowired
    public User2Controller(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user)  {


        try {
           User newUser = userService.register(user.getFirstName(),user.getLastName(),user.getUsername(),user.getEmail());
            return new ResponseEntity<>(newUser, OK);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND, "User not found");
        } catch (EmailExistException e) {
            throw new ResponseStatusException(CONFLICT,"Email already exist");
        } catch (UsernameExistException e) {
            throw new ResponseStatusException(CONFLICT,"Username already exist");
        }



    }
}
