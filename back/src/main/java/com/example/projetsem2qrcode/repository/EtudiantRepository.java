package com.example.projetsem2qrcode.repository;

import com.example.projetsem2qrcode.modele.Etudiant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EtudiantRepository extends MongoRepository<Etudiant,String> {

    Optional<Etudiant> findEtudiantByNumEtudiant(String numEtudiant);


}
