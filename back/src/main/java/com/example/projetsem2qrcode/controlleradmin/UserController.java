package com.example.projetsem2qrcode.controlleradmin;

import com.example.projetsem2qrcode.exceptions.DonneeManquanteException;
import com.example.projetsem2qrcode.exceptions.EmailInvalideException;
import com.example.projetsem2qrcode.exceptions.MauvaisFormatPseudoPasswordException;
import com.example.projetsem2qrcode.exceptions.UtilisateurDejaInscritException;
import com.example.projetsem2qrcode.modele.Utilisateur;
import com.example.projetsem2qrcode.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UtilisateurService utilisateurService;

    @PostMapping("/utilisateurs")
    public ResponseEntity<Utilisateur> inscription (@RequestBody Utilisateur utilisateur){
        try {
            utilisateurService.registerUtilisateur(utilisateur);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(utilisateur.getLogin()).toUri();

            return ResponseEntity.created(location).body(utilisateur);
        } catch (MauvaisFormatPseudoPasswordException | DonneeManquanteException | EmailInvalideException e) {
            return ResponseEntity.status(406).build();
        } catch (UtilisateurDejaInscritException e) {
            return ResponseEntity.status(409).build();
        }
    }


    @GetMapping("/utilisateurs/{login}")
    public ResponseEntity<Utilisateur> findUtilisateurByLogin(Principal principal, @PathVariable("login") String login) throws UsernameNotFoundException {
        if (!principal.getName().equals(login))
            return ResponseEntity.status(403).build();
        try {
            return ResponseEntity.ok().body(utilisateurService.findByUsername(login));
        }catch (UsernameNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }




}
