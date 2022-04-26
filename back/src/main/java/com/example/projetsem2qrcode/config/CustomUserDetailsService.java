package com.example.projetsem2qrcode.config;

import com.example.projetsem2qrcode.modele.Utilisateur;
import com.example.projetsem2qrcode.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomUserDetailsService implements UserDetailsService {
    private static final String[] ROLES_ADMIN = {"ETUDIANT", "ADMIN"};
    private static final String[] ROLES_ETUDIANT = {"ETUDIANT"};
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UtilisateurService utilisateurService;
    @Override
    public UserDetails loadUserByUsername(String username) {

        Utilisateur utilisateur = utilisateurService.findByUsername(username);

        String[] roles = utilisateur.isAdmin() ? ROLES_ADMIN : ROLES_ETUDIANT;
        return  User.builder()
                .username(utilisateur.getLogin())
                .password(passwordEncoder.encode(utilisateur.getPassword()))
                .roles(roles)
                .build();

    }
}
