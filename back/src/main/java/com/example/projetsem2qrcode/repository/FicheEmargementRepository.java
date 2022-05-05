package com.example.projetsem2qrcode.repository;

import com.example.projetsem2qrcode.modele.FicheEmargement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FicheEmargementRepository extends MongoRepository<FicheEmargement,String> {



    FicheEmargement findByNomCours(String nomCours);
}
