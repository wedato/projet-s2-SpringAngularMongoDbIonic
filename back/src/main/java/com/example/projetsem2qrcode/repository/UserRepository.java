package com.example.projetsem2qrcode.repository;

import com.example.projetsem2qrcode.modele.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

    User findUserByUsername(String username);

    User findUserByEmail(String email);
}
