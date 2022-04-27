package com.example.projetsem2qrcode.controlleradmin;

import com.example.projetsem2qrcode.exceptions.NomProfInvalideException;
import com.example.projetsem2qrcode.exceptions.PrenomProfInvalideException;
import com.example.projetsem2qrcode.exceptions.ProfDejaCreerException;
import com.example.projetsem2qrcode.exceptions.ProfInnexistantExcepton;
import com.example.projetsem2qrcode.modele.Prof;
import com.example.projetsem2qrcode.service.ProfService;
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
public class ProfController {

    @Autowired
    private ProfService profService;

    @PostMapping("/prof")
    public ResponseEntity<Prof> createProf(@RequestBody Prof prof){
        try {
            profService.saveProf(prof);
            URI nexLocation = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .path("/{idProf}")
                    .buildAndExpand(prof.getId())
                    .toUri();
            return ResponseEntity.created(nexLocation).body(prof);
        } catch (NomProfInvalideException | PrenomProfInvalideException e) {
            return ResponseEntity.status(400).build();
        } catch (ProfDejaCreerException e) {
            return ResponseEntity.status(409).build();
        }
    }

    @GetMapping("/prof")
    public ResponseEntity<List<Prof>> getAllProf(){
        return ResponseEntity.ok(profService.getAllProf());
    }

    @GetMapping("/prof/{nomProf}")
    public ResponseEntity<Prof> getProfByNom(@PathVariable String nomProf){
        try {
            Prof prof = profService.findProfByNom(nomProf);
            return ResponseEntity.status(302).body(prof);
        } catch (ProfInnexistantExcepton e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/prof")
    public ResponseEntity<HttpStatus> deleteAllProf(){
        profService.deleteAllProf();
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/prof/{nomProf}")
    public ResponseEntity<HttpStatus> deleteProfByName(@PathVariable String nomProf){
        try {
            profService.deleteProfByNom(nomProf);
            return ResponseEntity.status(204).build();
        } catch (ProfInnexistantExcepton e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/prof/{nomProf}")
    public ResponseEntity<HttpStatus> deleteAllCoursInProf(@PathVariable String nomProf){
        try {
            profService.deleteAllCoursForProf(nomProf);
            return ResponseEntity.status(204).build();
        } catch (ProfInnexistantExcepton e) {
            return ResponseEntity.status(404).build();
        }
    }
}
