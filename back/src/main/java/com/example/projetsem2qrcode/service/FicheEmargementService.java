package com.example.projetsem2qrcode.service;


import com.example.projetsem2qrcode.modele.FicheEmargement;
import com.example.projetsem2qrcode.modele.User;
import com.example.projetsem2qrcode.repository.FicheEmargementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FicheEmargementService {

    private FicheEmargementRepository ficheEmargementRepository;

    @Autowired
    public FicheEmargementService(FicheEmargementRepository ficheEmargementRepository) {
        this.ficheEmargementRepository = ficheEmargementRepository;
    }

    public List<FicheEmargement> getAllFiche(){
        return ficheEmargementRepository.findAll();
    }
    public List<User> getListeEtudiantSigneByCoursName(String coursName){
        return ficheEmargementRepository.findByNomCours(coursName).getListeEtudiantSigne();
    }
}
