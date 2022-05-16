package com.example.projetsem2qrcode.service;

import com.example.projetsem2qrcode.data.DataTest;
import com.example.projetsem2qrcode.data.DataTestImpl;
import com.example.projetsem2qrcode.modele.Cours;
import com.example.projetsem2qrcode.modele.GroupeTp;
import com.example.projetsem2qrcode.modele.Prof;
import com.example.projetsem2qrcode.repository.CoursRepository;
import com.example.projetsem2qrcode.repository.GroupeTpRepository;
import com.example.projetsem2qrcode.repository.ProfRepository;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

class CoursServiceTest {

    private CoursService coursServiceTest;

    private CoursRepository coursRepository;

    private GroupeTpRepository groupeTpRepository;

    private ProfRepository profRepository;


    CoursService instance;
    DataTest data;

    public CoursServiceTest(){
        data = new DataTestImpl();
    }

    @BeforeEach
    public void initialiseInstance(){

        instance = new CoursService(coursRepository, groupeTpRepository, profRepository);

    }
   /* @BeforeEach
    void setUp() {
        coursRepository = EasyMock.createMock(CoursRepository.class);
        groupeTpRepository = EasyMock.createMock(GroupeTpRepository.class);
        profRepository = EasyMock.createMock(ProfRepository.class);
        coursServiceTest = new CoursService(coursRepository,groupeTpRepository,profRepository);

    }*/

    @Test
    void createCours() {
        Prof prof = EasyMock.createMock(Prof.class);
        GroupeTp groupeTp = EasyMock.createMock(GroupeTp.class);
        Set<GroupeTp> lesGroupes = new HashSet<GroupeTp>();
        lesGroupes.add(groupeTp);
        Cours cours = new Cours("webService", prof , LocalDate.now(), LocalDate.now(), lesGroupes);
        CoursRepository coursRepository1 = EasyMock.createMock(CoursRepository.class);
        Assertions.assertDoesNotThrow(()->this.instance.createCours(cours));
    }

    @Test
    void findByNomDuCours() {
    }

    @Test
    void deleteCourByNom() {
    }

    @Test
    void getAllCours() {
        //String nom = "Pereda";
    }

    @Test
    void updateCours() {
    }

    @Test
    void addGroupeTPAuCours() {
    }

    @Test
    void addProfAuCours() {
    }
}