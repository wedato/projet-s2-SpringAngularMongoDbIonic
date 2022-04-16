package com.example.projetsem2qrcode.dao;

import com.example.projetsem2qrcode.modele.Cours;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CoursRepository extends MongoRepository<Cours,String> {

    Optional<Cours> findCoursByNom(String nom);
}
