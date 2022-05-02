package com.example.projetsem2qrcode.repository;

import com.example.projetsem2qrcode.modele.GroupeTp;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GroupeTpRepository extends MongoRepository<GroupeTp,String> {

    Optional<GroupeTp> findByNumeroGroupe(String numeroGroupe);
}
