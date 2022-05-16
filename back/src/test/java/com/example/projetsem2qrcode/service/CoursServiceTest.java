package com.example.projetsem2qrcode.service;

import com.example.projetsem2qrcode.repository.CoursRepository;
import com.example.projetsem2qrcode.repository.GroupeTpRepository;
import com.example.projetsem2qrcode.repository.ProfRepository;
import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoursServiceTest {

    private CoursService coursServiceTest;

    private CoursRepository coursRepository;

    private GroupeTpRepository groupeTpRepository;

    private ProfRepository profRepository;


    @BeforeEach
    void setUp() {
        coursRepository = EasyMock.createMock(CoursRepository.class);
        groupeTpRepository = EasyMock.createMock(GroupeTpRepository.class);
        profRepository = EasyMock.createMock(ProfRepository.class);
        coursServiceTest = new CoursService(coursRepository,groupeTpRepository,profRepository);

    }

    @Test
    void createCours() {

    }

    @Test
    void findByNomDuCours() {
    }

    @Test
    void deleteCourByNom() {
    }

    @Test
    void getAllCours() {
        String nom = "Pereda";

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