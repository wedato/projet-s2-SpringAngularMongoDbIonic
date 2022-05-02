package com.example.projetsem2qrcode.controlleradmin;


import com.example.projetsem2qrcode.exceptions.EtudiantInnexistantException;
import com.example.projetsem2qrcode.exceptions.NumEtudiantNonValideException;
import com.example.projetsem2qrcode.modele.Etudiant;
import com.example.projetsem2qrcode.service.EtudiantService;
import com.example.projetsem2qrcode.exceptions.NumEtudiantDejaPresentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class EtudiantController {

    @Autowired
    EtudiantService etudiantService;


    @PostMapping("/etudiants")
    public ResponseEntity<Etudiant> createEtudiant(@RequestBody Etudiant etudiant){
        try {
             etudiantService.saveEtudiant(etudiant);
            URI nextLocation = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .path("/{numEtu}")
                    .buildAndExpand(etudiant.getNumEtudiant())
                    .toUri();
            return ResponseEntity.created(nextLocation).body(etudiant);
        } catch (NumEtudiantDejaPresentException e) {
            return ResponseEntity.status(409).build();
        } catch (NumEtudiantNonValideException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/etudiants")
    public ResponseEntity<List<Etudiant>> getAllEtudiants() {
       return ResponseEntity.ok(etudiantService.getAllEtudiant());
    }



    @GetMapping("/etudiants/{numEtudiant}")
    public ResponseEntity<Etudiant> getEtudiant(@PathVariable("numEtudiant") String numEtudiant){
        try {
            Etudiant etudiant = etudiantService.findByNumEtudiant(numEtudiant);
            return ResponseEntity.status(202).body(etudiant);
        } catch (EtudiantInnexistantException e) {
            return ResponseEntity.status(404).build();
        }

    }

    @PutMapping("/etudiants/{numEtudiant}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable("numEtudiant") String numEtudiant, @RequestBody Etudiant etudiant){
        try {
            Etudiant etuUpdate = etudiantService.updateEtudiantByNumEtudiant(numEtudiant,etudiant);
            return ResponseEntity.status(202).body(etuUpdate);
        } catch (EtudiantInnexistantException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/etudiants/{numEtudiant}")
    public ResponseEntity<HttpStatus> deleteEtudiantByNumEtu(@PathVariable("numEtudiant") String numEtudiant) {
        try {
            etudiantService.deleteEtudiantByNumEtudiant(numEtudiant);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/etudiants")
    public ResponseEntity<HttpStatus> deleteAllEtudiant(){
        try {
            etudiantService.deleteAllEtudiant();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/etudiants")
    public ResponseEntity<HttpStatus> reinitEmargement(){
        etudiantService.reinitEmargement();
        return ResponseEntity.ok().build();
    }

}

