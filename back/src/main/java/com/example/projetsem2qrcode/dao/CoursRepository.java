package com.example.projetsem2qrcode.dao;

import com.example.projetsem2qrcode.modele.Cours;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoursRepository extends MongoRepository<Cours,String> {
}
