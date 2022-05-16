package com.example.projetsem2qrcode.service;

import com.example.projetsem2qrcode.exceptions.CoursDejaCreerException;
import com.example.projetsem2qrcode.modele.Cours;
import com.example.projetsem2qrcode.repository.CoursRepository;
import com.example.projetsem2qrcode.repository.GroupeTpRepository;
import com.example.projetsem2qrcode.repository.ProfRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CoursServiceTest {

    private CoursService underTest;

    @Mock
    private CoursRepository coursRepository;
    @Mock
    private GroupeTpRepository groupeTpRepository;
    @Mock
    private ProfRepository profRepository;


    @BeforeEach
    void setUp() {
        underTest  = new CoursService(coursRepository,groupeTpRepository,profRepository);
    }

    @Test
    void createCours() throws CoursDejaCreerException {
        // given
        Cours cours = new Cours();
        cours.setNom("Math");
        //when
        underTest.createCours(cours);
//        //then
        ArgumentCaptor<Cours> coursArgumentCaptor =
                ArgumentCaptor.forClass(Cours.class);
        verify(coursRepository).save(coursArgumentCaptor.capture());
        Cours capturedCours = coursArgumentCaptor.getValue();
        assertThat(capturedCours).isEqualTo(cours);


//        Prof prof = EasyMock.createMock(Prof.class);
//        GroupeTp groupeTp = EasyMock.createMock(GroupeTp.class);
//        Set<GroupeTp> lesGroupes = new HashSet<GroupeTp>();
//        lesGroupes.add(groupeTp);
//        Cours cours = new Cours("webService", prof , LocalDate.now(), LocalDate.now(), lesGroupes);
//        CoursRepository coursRepository1 = EasyMock.createMock(CoursRepository.class);
//        Assertions.assertDoesNotThrow(()->this.instance.createCours(cours));
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