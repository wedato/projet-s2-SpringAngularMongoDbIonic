package com.example.projetsem2qrcode.repository;

import com.example.projetsem2qrcode.modele.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends MongoRepository<Utilisateur,String> {

    Optional<Utilisateur> findUtilisateurByLogin(String login);


}
