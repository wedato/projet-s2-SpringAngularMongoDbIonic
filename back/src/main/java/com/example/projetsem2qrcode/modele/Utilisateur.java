package com.example.projetsem2qrcode.modele;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
//@Document
public class Utilisateur {
    @Id
    private String id;
    private String login;
    private String password;
    private boolean isAdmin;


    public Utilisateur(String login, String password) {
        this.login = login;
        this.password = password;

    }

}
