package com.example.projetsem2qrcode.controlleradmin;


import com.example.projetsem2qrcode.config.JWTTokenProvider;
import com.example.projetsem2qrcode.exceptions.EmailExistException;
import com.example.projetsem2qrcode.exceptions.UserNotFoundException;
import com.example.projetsem2qrcode.exceptions.UsernameExistException;
import com.example.projetsem2qrcode.modele.User;
import com.example.projetsem2qrcode.modele.UserPrincipal;
import com.example.projetsem2qrcode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

    private HttpHeaders getJwtHeaders(UserPrincipal user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWT_TOKEN_HEADER,jwtTokenProvider.generateJwtToken(user));
        return httpHeaders;
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
    }
}
