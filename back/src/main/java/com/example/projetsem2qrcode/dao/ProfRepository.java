package com.example.projetsem2qrcode.dao;

import com.example.projetsem2qrcode.modele.Prof;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProfRepository extends MongoRepository<Prof,String> {

    Optional<Prof> findByNom(String nomProf);

}
