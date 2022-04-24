package com.example.projetsem2qrcode.config;

import com.example.projetsem2qrcode.dao.UtilisateurRepository;
import com.example.projetsem2qrcode.modele.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {
    private static final String[] ROLES_ADMIN = {"ETUDIANT", "ADMIN"};
    private static final String[] ROLES_ETUDIANT = {"ETUDIANT"};
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findUtilisateurByLogin(username);
        if (utilisateurOptional.isEmpty())
            throw new UsernameNotFoundException("User " + username + " not found");
        Utilisateur utilisateur = utilisateurOptional.get();
        String[] roles = utilisateur.isAdmin() ? ROLES_ADMIN : ROLES_ETUDIANT;
        return User.builder()
                .username(utilisateur.getLogin())
                .password(passwordEncoder.encode(utilisateur.getPassword()))
                .roles(roles)
                .build();
    }
}
