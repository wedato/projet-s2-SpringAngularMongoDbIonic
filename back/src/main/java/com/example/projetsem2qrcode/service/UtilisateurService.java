package com.example.projetsem2qrcode.service;


import com.example.projetsem2qrcode.repository.UtilisateurRepository;
import com.example.projetsem2qrcode.exceptions.DonneeManquanteException;
import com.example.projetsem2qrcode.exceptions.EmailInvalideException;
import com.example.projetsem2qrcode.exceptions.MauvaisFormatPseudoPasswordException;
import com.example.projetsem2qrcode.exceptions.UtilisateurDejaInscritException;
import com.example.projetsem2qrcode.modele.Utilisateur;
import com.example.projetsem2qrcode.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    UtilisateurRepository utilisateurRepository;




    public Utilisateur registerUtilisateur(Utilisateur utilisateur) throws UtilisateurDejaInscritException, MauvaisFormatPseudoPasswordException, DonneeManquanteException, EmailInvalideException {
        if (utilisateur.getLogin() == null || utilisateur.getPassword() == null || utilisateur.getLogin().isBlank() || utilisateur.getPassword().isBlank())
            throw new DonneeManquanteException();

        if (utilisateurRepository.findUtilisateurByLogin(utilisateur.getLogin()).isPresent())
            throw new UtilisateurDejaInscritException();
        if (!EmailUtils.verifier(utilisateur.getLogin()))
            throw new EmailInvalideException();

        if (utilisateur.getLogin().split("@")[1].equals("univ-orleans.fr"))
           return registerAdmin(utilisateur);
        System.out.println(utilisateur.getLogin().split("@")[1]);
        return registerEtudiant(utilisateur);

    }
    public Utilisateur registerAdmin(Utilisateur utilisateur)  {
        utilisateur.setAdmin(true);
        return utilisateurRepository.save(utilisateur);

    }
    public Utilisateur registerEtudiant(Utilisateur utilisateur) {

        utilisateur.setAdmin(false);
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur findByUsername(String username) throws UsernameNotFoundException{

        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findUtilisateurByLogin(username);
        if (utilisateurOptional.isEmpty())
            throw new UsernameNotFoundException("User " + username + " not found");
        return utilisateurOptional.get();

    }
}
