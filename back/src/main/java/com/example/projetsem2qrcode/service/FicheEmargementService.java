package com.example.projetsem2qrcode.service;


import com.example.projetsem2qrcode.modele.FicheEmargement;
import com.example.projetsem2qrcode.modele.User;
import com.example.projetsem2qrcode.repository.FicheEmargementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FicheEmargementService {

    private FicheEmargementRepository ficheEmargementRepository;
    private UserService userService;

    @Autowired
    public FicheEmargementService(FicheEmargementRepository ficheEmargementRepository, UserService userService) {
        this.ficheEmargementRepository = ficheEmargementRepository;
        this.userService = userService;
    }

    public List<FicheEmargement> getAllFiche(){
        return ficheEmargementRepository.findAll();
    }
    public List<User> getListeEtudiantSigneByCoursName(String coursName){
        return ficheEmargementRepository.findByNomCours(coursName).getListeEtudiantSigne();
    }

    public void signerFicheEmargementDebut(String idFiche, String username){
        Optional<FicheEmargement> ficheEmargement = ficheEmargementRepository.findById(idFiche);
        User user = userService.findUserByUsername(username);
        ficheEmargement.get().getListeEtudiantSigne().add(user);
        ficheEmargementRepository.save(ficheEmargement.get());
//        user.setSignedEndClass(true);


    }
}
