package com.example.projetsem2qrcode.controlleradmin;


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
        Etudiant etudiant = etudiantService.findByNumEtudiant(numEtudiant);
        return etudiant == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(etudiant,HttpStatus.FOUND) ;
    }

    @PutMapping("/etudiants/{numEtudiant}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable("numEtudiant") String numEtudiant, @RequestBody Etudiant etudiant){
        Etudiant etuUpdate = etudiantService.updateEtudiantByNumEtudiant(numEtudiant,etudiant);
        return etuUpdate == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(etuUpdate,HttpStatus.OK);
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

    }

