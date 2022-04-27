package com.example.projetsem2qrcode.controlleradmin;

import com.example.projetsem2qrcode.exceptions.*;
import com.example.projetsem2qrcode.modele.Cours;
import com.example.projetsem2qrcode.service.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CoursController {

    @Autowired
    CoursService coursService;

    @PostMapping("/cours")
    public ResponseEntity<Cours> createCours(@RequestBody Cours cours){
        try {
            Cours _cours = coursService.createCours(cours);
            return new ResponseEntity<>(_cours, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cours")
    public ResponseEntity<List<Cours>> getAllCours(){
        List<Cours> cours = coursService.getAllCours();
        return cours.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(cours,HttpStatus.OK);
    }

    @GetMapping("/cours/{nomCours}")
    public ResponseEntity<Cours> getCoursByNom(@PathVariable("nomCours") String nomCours){
        Cours cours = coursService.findByNomDuCours(nomCours);
        return cours == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(cours,HttpStatus.FOUND);
    }

    @PutMapping("/cours/{nomCours}")
    public ResponseEntity<Cours> updateCours(@PathVariable("nomCours") String nomCours, @RequestBody Cours cours){
        try {
            coursService.updateCours(nomCours,cours);
            return ResponseEntity.status(202).build();
        } catch (CoursInnexistantException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/cours/{nomCours}")
    public ResponseEntity<HttpStatus> deleteCoursByNom(@PathVariable("nomCours") String nomCours){
        try{
            coursService.deleteCourByNom(nomCours);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cours")
    public ResponseEntity<HttpStatus> deleteAllCours(){
        try {
            coursService.deleteAllCours();
            return new ResponseEntity<> (HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cours/{nomCours}")
    public ResponseEntity<Cours> addGroupeTpInCours(@PathVariable("nomCours") String nomCours, @RequestBody String numeroGroupe){
        try {
            coursService.addGroupeTPAuCours(nomCours,numeroGroupe);
            return ResponseEntity.status(202).build();
        } catch (GroupeInnexistantException | CoursInnexistantException e) {
            return ResponseEntity.status(404).build();
        } catch (GroupeTpDejaAjouterException e) {
            return ResponseEntity.status(409).build();
        }
    }

    @PutMapping("/cours/{nomCours}")
    public ResponseEntity<Cours> addProfInCours(@PathVariable("nomCours") String nomCours, @RequestBody String nomProf){
        try {
            coursService.addProfAuCours(nomCours,nomProf);
            return ResponseEntity.status(202).build();
        } catch (ProfInnexistantExcepton | CoursInnexistantException e) {
            return ResponseEntity.status(404).build();
        } catch (ProfDejaAjouterException e) {
            return ResponseEntity.status(409).build();
        }
    }
}
