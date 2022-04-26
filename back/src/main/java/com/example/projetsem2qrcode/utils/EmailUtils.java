package com.example.projetsem2qrcode.utils;

import java.util.regex.Pattern;

public class EmailUtils {

    public static boolean verifier(String emailAddress) {
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
