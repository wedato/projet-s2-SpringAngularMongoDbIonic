package com.example.projetsem2qrcode.controlleradmin;


import com.example.projetsem2qrcode.exceptions.*;
import com.example.projetsem2qrcode.modele.GroupeTp;
import com.example.projetsem2qrcode.service.GroupeTpService;
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
public class GroupeTpController {

    @Autowired
    GroupeTpService groupeTpService;

    @PostMapping("/groupetp")
    public ResponseEntity<GroupeTp> createGroupeTp(@RequestBody GroupeTp groupeTp){
        try {
            groupeTpService.saveGroupeTp(groupeTp);
            URI nextLocation = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .path("/{numeroGroupe}").buildAndExpand(groupeTp.getNumeroGroupe()).toUri();
            return ResponseEntity.created(nextLocation).body(groupeTp);
        } catch (GroupeDejaCreerException e) {
            return ResponseEntity.status(409).build();
        } catch (NomGroupeNonValideException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/groupetp")
    public ResponseEntity<List<GroupeTp>> getAllGroupeTp() {
        return ResponseEntity.ok(groupeTpService.getAllGroupeTp());
    }

    @PutMapping("/groupetp/{numeroGroupe}")
    public ResponseEntity<GroupeTp> addEtudiantAuGroupeTp(@PathVariable("numeroGroupe") String idGroupe ,@RequestBody String numEtudiant){
        try {
            groupeTpService.addEtudiantInGroupe(idGroupe,numEtudiant);
            return ResponseEntity.status(202).build();
        } catch (GroupeInnexistantException | EtudiantInnexistantException e) {
            return ResponseEntity.status(404).build();
        } catch (EtudiantDejaDansUnGroupeException e) {
            return ResponseEntity.status(409).build();
        }
    }

    @GetMapping("/groupetp/{numeroGroupe}")
    public ResponseEntity<GroupeTp> getGroupeTp(@PathVariable("numeroGroupe")String numGroupe){
        try {
            groupeTpService.findGroupeByNumGroupe(numGroupe);
            return ResponseEntity.status(302).build();
        } catch (GroupeInnexistantException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/groupetp/{numeroGroupe}")
    public ResponseEntity<HttpStatus> deleteGroupeByNumGroupe(@PathVariable("numeroGroupe") String numGroupe) {
        try {
            groupeTpService.deleteGroupeByNumGroupe(numGroupe);
            return ResponseEntity.status(204).build();
        } catch (GroupeInnexistantException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/groupetp")
    public ResponseEntity<HttpStatus> deleteAllGroupe(){
        groupeTpService.deleteAllGroupeTp();
        return ResponseEntity.status(204).build();
    }


    @DeleteMapping("/groupetp/{numeroGroupe}/etudiant")
    public ResponseEntity<HttpStatus> deleteAllEtudiantInGroupe(@PathVariable("numeroGroupe") String numeroGroupe){
        try {
            groupeTpService.deleteAllEtudiantInGroupeTp(numeroGroupe);
            return ResponseEntity.status(204).build();
        } catch (GroupeInnexistantException e) {
            return ResponseEntity.status(404).build();
        }
    }

}

