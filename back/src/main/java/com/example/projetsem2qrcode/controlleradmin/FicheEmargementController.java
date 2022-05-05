package com.example.projetsem2qrcode.controlleradmin;


import com.example.projetsem2qrcode.modele.User;
import com.example.projetsem2qrcode.service.FicheEmargementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/fiche")
public class FicheEmargementController {


    private FicheEmargementService ficheEmargementService;

    @Autowired
    public FicheEmargementController(FicheEmargementService ficheEmargementService) {
        this.ficheEmargementService = ficheEmargementService;
    }

    @GetMapping("/liste/{nomCours}")
    public ResponseEntity<List<User>> getListe(@PathVariable String nomCours){
        List<User> listeUserSigne = ficheEmargementService.getListeEtudiantSigneByCoursName(nomCours);
        return new ResponseEntity<>(listeUserSigne, HttpStatus.OK);
    }
}
