package com.example.projetsem2qrcode.config;
import static com.example.projetsem2qrcode.constant.Authority.*;
public enum Role {
    ROLE_USER(USER_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES);


    private String[] authorities;

    Role(String... authorities){
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
