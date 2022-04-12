package com.example.projetsem2qrcode.controlleradmin;

import com.example.projetsem2qrcode.dao.CoursRepository;
import com.example.projetsem2qrcode.modele.Cours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CoursController {

    @Autowired
    CoursRepository coursRepository;

    @PostMapping("/cours")
    public ResponseEntity<Cours> createCours(@RequestBody Cours cours){
        try {
            Cours _cours = coursRepository.save(new Cours(cours.getNom(),
                    cours.getProf(),
                    cours.getHeureDebut(),
                    cours.getHeureFin(),
                    cours.getLesGroupes()));
            return new ResponseEntity<>(_cours, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
