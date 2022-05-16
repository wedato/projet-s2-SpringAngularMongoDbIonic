package com.example.projetsem2qrcode.repository;

import com.example.projetsem2qrcode.modele.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {

    @Autowired
    UserRepository classeATest;

    @Test
    void findUserByUsernameOk() {
        User user = new User();
        user.setUsername("Wedat");
        classeATest.save(user);

        User verifUserTrouve = classeATest.findUserByUsername("Wedat");
        Assertions.assertEquals(verifUserTrouve, user);
    }
}