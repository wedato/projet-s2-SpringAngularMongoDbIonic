package com.example.projetsem2qrcode.service;


import com.example.projetsem2qrcode.dao.UtilisateurRepository;
import com.example.projetsem2qrcode.exceptions.MauvaisFormatPseudoPasswordException;
import com.example.projetsem2qrcode.exceptions.UtilisateurDejaInscritException;
import com.example.projetsem2qrcode.modele.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Predicate;

@Service
public class UtilisateurService {

    @Autowired
    UtilisateurRepository utilisateurRepository;
    Predicate<String> isOk = s -> (s!=null) && (s.length()>=2);


    public Utilisateur registerAdmin(String pseudo, String password) throws MauvaisFormatPseudoPasswordException, UtilisateurDejaInscritException {
        if (!isOk.test(pseudo) || !isOk.test(password))
            throw new MauvaisFormatPseudoPasswordException();
        if (utilisateurRepository.findUtilisateurByLogin(pseudo).isPresent())
            throw new UtilisateurDejaInscritException();
        Utilisateur utilisateur = new Utilisateur(pseudo,password,true);
        return utilisateurRepository.save(utilisateur);

    }
    public Utilisateur registerEtudiant(String pseudo, String password) throws MauvaisFormatPseudoPasswordException, UtilisateurDejaInscritException {
        if (!isOk.test(pseudo) || !isOk.test(password))
            throw new MauvaisFormatPseudoPasswordException();
        if (utilisateurRepository.findUtilisateurByLogin(pseudo).isPresent())
            throw new UtilisateurDejaInscritException();
        Utilisateur utilisateur = new Utilisateur(pseudo,password,false);
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur findByUsername(String username) throws UsernameNotFoundException{

        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findUtilisateurByLogin(username);
        if (utilisateurOptional.isEmpty())
            throw new UsernameNotFoundException("User " + username + " not found");
        return utilisateurOptional.get();

    }
}
