package com.example.projetsem2qrcode.service;

import com.example.projetsem2qrcode.exceptions.CoursDejaCreerException;
import com.example.projetsem2qrcode.exceptions.CoursInnexistantException;
import com.example.projetsem2qrcode.modele.Cours;
import com.example.projetsem2qrcode.modele.Prof;
import com.example.projetsem2qrcode.repository.CoursRepository;
import com.example.projetsem2qrcode.repository.GroupeTpRepository;
import com.example.projetsem2qrcode.repository.ProfRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CoursService.class})
@ExtendWith(MockitoExtension.class)
class CoursServiceTest {

    @Autowired
    private CoursService coursService;

    private CoursService underTest;

    @Mock
    private CoursRepository coursRepository;
    @Mock
    private GroupeTpRepository groupeTpRepository;
    @Mock
    private ProfRepository profRepository;


    @BeforeEach
    void setUp() {
        underTest = new CoursService(coursRepository, groupeTpRepository, profRepository);
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

    /**
     * Method under test: {@link CoursService#findByNomDuCours(String)}
     */
    @Test
    void testFindByNomDuCoursok() throws CoursInnexistantException {
        // given
        Cours cours = new Cours();
        cours.setHeureDebut(null);
        cours.setHeureFin(null);
        cours.setId("42");
        cours.setLesGroupes(new HashSet<>());
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        Optional<Cours> ofResult = Optional.of(cours);
        //when
        when(this.coursRepository.findCoursByNom((String) any())).thenReturn(ofResult);
        //assert
        assertSame(cours, this.coursService.findByNomDuCours("Nom Cours"));
        verify(this.coursRepository).findCoursByNom((String) any());
    }

    /**
     * Method under test: {@link CoursService#findByNomDuCours(String)}
     */
    @Test
    void testFindByNomDuCoursKo1() throws CoursInnexistantException {
        // when
        when(this.coursRepository.findCoursByNom((String) any())).thenReturn(Optional.empty());
        //assert
        assertThrows(CoursInnexistantException.class, () -> this.coursService.findByNomDuCours("Nom Cours"));
        verify(this.coursRepository).findCoursByNom((String) any());
    }

    /**
     * Method under test: {@link CoursService#deleteAllCours()}
     */
    @Test
    void testDeleteAllCours() {
        //when
        doNothing().when(this.coursRepository).deleteAll();
        this.coursService.deleteAllCours();
        //asser
        verify(this.coursRepository).deleteAll();
        assertTrue(this.coursService.getAllCours().isEmpty());
    }

}