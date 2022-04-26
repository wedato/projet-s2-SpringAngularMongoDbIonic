package com.example.projetsem2qrcode.controlleradmin;


import com.example.projetsem2qrcode.exceptions.ExceptionHandling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/user")
public class User2Controller extends ExceptionHandling {

    @GetMapping("/home")
    public String showUser(){
        return "application works";
    }
}
