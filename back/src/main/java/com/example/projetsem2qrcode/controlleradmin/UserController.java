package com.example.projetsem2qrcode.controlleradmin;

import com.example.projetsem2qrcode.exceptions.MauvaisFormatPseudoPasswordException;
import com.example.projetsem2qrcode.exceptions.UtilisateurDejaInscritException;
import com.example.projetsem2qrcode.modele.Utilisateur;
import com.example.projetsem2qrcode.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UtilisateurService utilisateurService;

    @PostMapping("/admins")
    public ResponseEntity<Utilisateur> inscriptionAdmin (@RequestBody Utilisateur utilisateur){
        try {
            utilisateurService.registerAdmin(utilisateur.getLogin(), utilisateur.getPassword());
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(utilisateur.getLogin()).toUri();
            return ResponseEntity.created(location).body(utilisateur);
        } catch (MauvaisFormatPseudoPasswordException e) {
            return ResponseEntity.badRequest().build();
        } catch (UtilisateurDejaInscritException e) {
            return ResponseEntity.status(409).build();
        }
    }
}
