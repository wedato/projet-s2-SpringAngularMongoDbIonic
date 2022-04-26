package com.example.projetsem2qrcode;

import com.example.projetsem2qrcode.repository.EtudiantRepository;
import com.example.projetsem2qrcode.repository.UtilisateurRepository;
import com.example.projetsem2qrcode.modele.Utilisateur;
import com.example.projetsem2qrcode.service.UtilisateurService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjetSem2QRcodeApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProjetSem2QRcodeApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
   CommandLineRunner start(EtudiantRepository etudiantRepository, UtilisateurRepository utilisateurRepository, UtilisateurService utilisateurService){
        return args -> {
            etudiantRepository.deleteAll();
            utilisateurRepository.deleteAll();


            utilisateurService.registerAdmin(new Utilisateur("admin", "admin"));
            utilisateurService.registerEtudiant(new Utilisateur("user1", "password"));




        };
   }




}
