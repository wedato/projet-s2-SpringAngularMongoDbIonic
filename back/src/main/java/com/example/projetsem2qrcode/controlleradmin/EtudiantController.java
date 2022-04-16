package com.example.projetsem2qrcode.controlleradmin;


import com.example.projetsem2qrcode.modele.Etudiant;
import com.example.projetsem2qrcode.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class EtudiantController {

    @Autowired
    EtudiantService etudiantService;


    @PostMapping("/etudiants")
    public ResponseEntity<Etudiant> createEtudiant(@RequestBody Etudiant etudiant){
        Etudiant saveEtu = etudiantService.saveEtudiant(etudiant);
        return saveEtu == null ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(saveEtu,HttpStatus.CREATED);
    }

    @GetMapping("/etudiants")
    public ResponseEntity<List<Etudiant>> getAllEtudiants() {
        List<Etudiant> etudiants = etudiantService.getAllEtudiant();
        return etudiants.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(etudiants,HttpStatus.OK);
    }

   /* @GetMapping("/etudiants/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable("id") String id){
        Optional<Etudiant> etudiantData = etudiantRepository.findById(id);
        if(etudiantData.isPresent()){
            return new ResponseEntity<>(etudiantData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    @GetMapping("/etudiants/numEtu/{numEtudiant}")
    public ResponseEntity<Etudiant> getEtudiantByNumEtudiant(@PathVariable("numEtudiant") String numEtudiant){
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

