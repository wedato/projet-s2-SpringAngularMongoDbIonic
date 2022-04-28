package com.example.projetsem2qrcode.controlleradmin;


import com.example.projetsem2qrcode.config.JWTTokenProvider;
import com.example.projetsem2qrcode.exceptions.EmailExistException;
import com.example.projetsem2qrcode.exceptions.EmailNotFoundException;
import com.example.projetsem2qrcode.exceptions.UserNotFoundException;
import com.example.projetsem2qrcode.exceptions.UsernameExistException;
import com.example.projetsem2qrcode.modele.User;
import com.example.projetsem2qrcode.modele.UserPrincipal;
import com.example.projetsem2qrcode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

import static com.example.projetsem2qrcode.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.*;

// format date indice?
//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss" ,timezone = "Europe/Paris")

@RestController
@RequestMapping(value="/user")
public class UserController  {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user)  {
        try {
            authenticate(user.getUsername(),user.getPassword());
            User loginUser = userService.findUserByUsername(user.getUsername());
            UserPrincipal userPrincipal = new UserPrincipal(loginUser);
            HttpHeaders jwtHeader = getJwtHeaders(userPrincipal);
            return new ResponseEntity<>(loginUser, jwtHeader, OK);
        }
        catch (LockedException e){
            throw new ResponseStatusException(LOCKED, "Your account have been locked");
        }
        catch (BadCredentialsException e){
            throw new ResponseStatusException(BAD_REQUEST,"Password or username incorrect , try again");
        }
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

    @PostMapping("/add")
    public ResponseEntity<User> addNewUser(@RequestParam ("firstName") String firstName,
                                           @RequestParam ("lastName") String lastName,
                                           @RequestParam ("username") String username,
                                           @RequestParam ("email") String email,
                                           @RequestParam ("role") String role,
                                           @RequestParam ("isActive") String isActive,
                                           @RequestParam ("isNonLocked") String isNonLocked,
                                           @RequestParam (value = "profileImage", required = false) MultipartFile profileImage) {

        try {
            User newUser = userService.addNewUser(firstName,lastName,username,email,role,Boolean.parseBoolean(isActive),Boolean.parseBoolean(isNonLocked), profileImage);
            return new ResponseEntity<>(newUser, OK);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND, "User not found");
        } catch (EmailExistException e) {
            throw new ResponseStatusException(CONFLICT,"Email already exist");
        } catch (UsernameExistException e) {
            throw new ResponseStatusException(CONFLICT,"Username already exist");
        } catch (IOException e) {
            throw new ResponseStatusException(BAD_REQUEST,"Issue with the file upload");
        }

    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestParam ("currentUsername") String currentUsername,
                                           @RequestParam ("firstName") String firstName,
                                           @RequestParam ("lastName") String lastName,
                                           @RequestParam ("username") String username,
                                           @RequestParam ("email") String email,
                                           @RequestParam ("role") String role,
                                           @RequestParam ("isActive") String isActive,
                                           @RequestParam ("isNonLocked") String isNonLocked,
                                           @RequestParam (value = "profileImage", required = false) MultipartFile profileImage) {

        try {
            User updateUser = userService.updateUser(currentUsername,firstName,lastName,username,email,role,Boolean.parseBoolean(isActive),Boolean.parseBoolean(isNonLocked), profileImage);
            return new ResponseEntity<>(updateUser, OK);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND, "User not found");
        } catch (EmailExistException e) {
            throw new ResponseStatusException(CONFLICT,"Email already exist");
        } catch (UsernameExistException e) {
            throw new ResponseStatusException(CONFLICT,"Username already exist");
        } catch (IOException e) {
            throw new ResponseStatusException(BAD_REQUEST,"Issue with the file upload");
        }

    }

    @PostMapping("/updateProfileImage")
    public ResponseEntity<User> updateProfileImage(@RequestParam ("username") String username, @RequestParam (value = "profileImage") MultipartFile profileImage) {
        try {
            User user = userService.updateProfileImage(username,profileImage);
            return new ResponseEntity<>(user, OK);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND, "User not found");
        } catch (EmailExistException e) {
            throw new ResponseStatusException(CONFLICT,"Email already exist");
        } catch (UsernameExistException e) {
            throw new ResponseStatusException(CONFLICT,"Username already exist");
        } catch (IOException e) {
            throw new ResponseStatusException(BAD_REQUEST,"Issue with the file upload");
        }
        }




    @GetMapping("/find/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username){
        User user = userService.findUserByUsername(username);
        return new ResponseEntity<>(user,OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users,OK);
    }

    @GetMapping("/resetPassword/{email}")
    public ResponseEntity<?> resetPassword(@PathVariable("email") String email){
        try {
            userService.resetPassword(email);
            return new ResponseEntity<>("Email sent to" + email , OK);
        } catch (EmailNotFoundException e) {
            throw new  ResponseStatusException(NOT_FOUND, email + " : that email was not found");
        }
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id){
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted Sucessfully", NO_CONTENT);
    }

    private HttpHeaders getJwtHeaders(UserPrincipal user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWT_TOKEN_HEADER,jwtTokenProvider.generateJwtToken(user));
        return httpHeaders;
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
    }
}
