package com.example.projetsem2qrcode.service;

import com.example.projetsem2qrcode.exceptions.*;
import com.example.projetsem2qrcode.modele.Cours;
import com.example.projetsem2qrcode.modele.GroupeTp;
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

import java.time.LocalDate;
import java.util.ArrayList;
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

    @Mock
    private CoursRepository coursRepository;
    @Mock
    private GroupeTpRepository groupeTpRepository;
    @Mock
    private ProfRepository profRepository;


    @BeforeEach
    void setUp() {
        coursService = new CoursService(coursRepository, groupeTpRepository, profRepository);
    }

    @Test
    void createCours() throws CoursDejaCreerException {
        // given
        Cours cours = new Cours();
        cours.setNom("Math");
        //when
        coursService.createCours(cours);
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
    void testFindByNomDuCoursOk() throws CoursInnexistantException {
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

    /**
     * Method under test: {@link CoursService#deleteCourByNom(String)}
     */
    @Test
    void testDeleteCourByNomOk() throws CoursInnexistantException {
        //given
        Cours cours = new Cours();
        cours.setHeureDebut(null);
        cours.setHeureFin(null);
        cours.setId("42");
        cours.setLesGroupes(new HashSet<>());
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        Optional<Cours> ofResult = Optional.of(cours);
        doNothing().when(this.coursRepository).deleteById((String) any());
        //when
        when(this.coursRepository.findCoursByNom((String) any())).thenReturn(ofResult);
        //assert
        assertThrows(CoursInnexistantException.class, () -> this.coursService.deleteCourByNom("Nom Cours"));
        verify(this.coursRepository).findCoursByNom((String) any());
        verify(this.coursRepository).deleteById((String) any());
    }

    /**
     * Method under test: {@link CoursService#deleteCourByNom(String)}
     */
    @Test
    void testDeleteCourByNomKo1() throws CoursInnexistantException {
        //when
        doNothing().when(this.coursRepository).deleteById((String) any());
        when(this.coursRepository.findCoursByNom((String) any())).thenReturn(Optional.empty());
        //Assert
        assertThrows(CoursInnexistantException.class, () -> this.coursService.deleteCourByNom("Nom Cours"));
        verify(this.coursRepository).findCoursByNom((String) any());
    }

    /**
     * Method under test: {@link CoursService#getAllCours()}
     */
    @Test
    void testGetAllCours() {
        //when
        when(this.coursRepository.findAll()).thenReturn(new ArrayList<>());
        //assert
        assertTrue(this.coursService.getAllCours().isEmpty());
        verify(this.coursRepository).findAll();
    }

    /**
     * Method under test: {@link CoursService#updateCours(String, Cours)}
     */
    @Test
    void testUpdateCoursOk() throws CoursInnexistantException {
        //given
        Cours cours = new Cours();
        cours.setHeureDebut(null);
        cours.setHeureFin(null);
        cours.setId("42");
        cours.setLesGroupes(new HashSet<>());
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        Optional<Cours> ofResult = Optional.of(cours);

        //given
        Cours cours1 = new Cours();
        cours1.setHeureDebut(null);
        cours1.setHeureFin(null);
        cours1.setId("42");
        cours1.setLesGroupes(new HashSet<>());
        cours1.setNom("Nom");
        cours1.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        //when
        when(this.coursRepository.save((Cours) any())).thenReturn(cours1);
        when(this.coursRepository.findCoursByNom((String) any())).thenReturn(ofResult);

        //given
        Cours cours2 = new Cours();
        cours2.setHeureDebut(null);
        cours2.setHeureFin(null);
        cours2.setId("42");
        cours2.setLesGroupes(new HashSet<>());
        cours2.setNom("Nom");
        cours2.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        //assert
        assertSame(cours1, this.coursService.updateCours("Nom Cours", cours2));
        verify(this.coursRepository).save((Cours) any());
        verify(this.coursRepository).findCoursByNom((String) any());
    }

    /**
     * Method under test: {@link CoursService#updateCours(String, Cours)}
     */
    @Test
    void testUpdateCoursKo1() throws CoursInnexistantException {
        //given
        Cours cours = new Cours();
        cours.setHeureDebut(null);
        cours.setHeureFin(null);
        cours.setId("42");
        cours.setLesGroupes(new HashSet<>());
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        //when
        when(this.coursRepository.save((Cours) any())).thenReturn(cours);
        when(this.coursRepository.findCoursByNom((String) any())).thenReturn(Optional.empty());

        //given
        Cours cours1 = new Cours();
        cours1.setHeureDebut(null);
        cours1.setHeureFin(null);
        cours1.setId("42");
        cours1.setLesGroupes(new HashSet<>());
        cours1.setNom("Nom");
        cours1.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        //assert
        assertThrows(CoursInnexistantException.class, () -> this.coursService.updateCours("Nom Cours", cours1));
        verify(this.coursRepository).findCoursByNom((String) any());
    }

    /**
     * Method under test: {@link CoursService#addGroupeTPAuCours(String, String)}
     */
    @Test
    void testAddGroupeTPAuCours()
            throws CoursInnexistantException, GroupeInnexistantException, GroupeTpDejaAjouterException {
        when(this.groupeTpRepository.findByNumeroGroupe((String) any()))
                .thenReturn(Optional.of(new GroupeTp("Numero Groupe")));

        Cours cours = new Cours();
        cours.setHeureDebut(LocalDate.ofEpochDay(1L));
        cours.setHeureFin(LocalDate.ofEpochDay(1L));
        cours.setId("42");
        cours.setLesGroupes(new HashSet<>());
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        Optional<Cours> ofResult = Optional.of(cours);

        Cours cours1 = new Cours();
        cours1.setHeureDebut(LocalDate.ofEpochDay(1L));
        cours1.setHeureFin(LocalDate.ofEpochDay(1L));
        cours1.setId("42");
        cours1.setLesGroupes(new HashSet<>());
        cours1.setNom("Nom");
        cours1.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        when(this.coursRepository.save((Cours) any())).thenReturn(cours1);
        when(this.coursRepository.findCoursByNom((String) any())).thenReturn(ofResult);
        assertSame(cours1, this.coursService.addGroupeTPAuCours("Nom Cours", "Numero Groupe"));
        verify(this.groupeTpRepository).findByNumeroGroupe((String) any());
        verify(this.coursRepository).save((Cours) any());
        verify(this.coursRepository).findCoursByNom((String) any());
    }

    /**
     * Method under test: {@link CoursService#addGroupeTPAuCours(String, String)}
     */
    @Test
    void testAddGroupeTPAuCours2()
            throws CoursInnexistantException, GroupeInnexistantException, GroupeTpDejaAjouterException {
        when(this.groupeTpRepository.findByNumeroGroupe((String) any())).thenReturn(Optional.empty());

        Cours cours = new Cours();
        cours.setHeureDebut(LocalDate.ofEpochDay(1L));
        cours.setHeureFin(LocalDate.ofEpochDay(1L));
        cours.setId("42");
        cours.setLesGroupes(new HashSet<>());
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        Optional<Cours> ofResult = Optional.of(cours);

        Cours cours1 = new Cours();
        cours1.setHeureDebut(LocalDate.ofEpochDay(1L));
        cours1.setHeureFin(LocalDate.ofEpochDay(1L));
        cours1.setId("42");
        cours1.setLesGroupes(new HashSet<>());
        cours1.setNom("Nom");
        cours1.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        when(this.coursRepository.save((Cours) any())).thenReturn(cours1);
        when(this.coursRepository.findCoursByNom((String) any())).thenReturn(ofResult);
        assertThrows(GroupeInnexistantException.class,
                () -> this.coursService.addGroupeTPAuCours("Nom Cours", "Numero Groupe"));
        verify(this.groupeTpRepository).findByNumeroGroupe((String) any());
        verify(this.coursRepository).findCoursByNom((String) any());
    }

    /**
     * Method under test: {@link CoursService#addGroupeTPAuCours(String, String)}
     */
    @Test
    void testAddGroupeTPAuCours3()
            throws CoursInnexistantException, GroupeInnexistantException, GroupeTpDejaAjouterException {
        when(this.groupeTpRepository.findByNumeroGroupe((String) any()))
                .thenReturn(Optional.of(new GroupeTp("Numero Groupe")));

        HashSet<GroupeTp> groupeTpSet = new HashSet<>();
        groupeTpSet.add(new GroupeTp("Numero Groupe"));

        Cours cours = new Cours();
        cours.setHeureDebut(LocalDate.ofEpochDay(1L));
        cours.setHeureFin(LocalDate.ofEpochDay(1L));
        cours.setId("42");
        cours.setLesGroupes(groupeTpSet);
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        Optional<Cours> ofResult = Optional.of(cours);

        Cours cours1 = new Cours();
        cours1.setHeureDebut(LocalDate.ofEpochDay(1L));
        cours1.setHeureFin(LocalDate.ofEpochDay(1L));
        cours1.setId("42");
        cours1.setLesGroupes(new HashSet<>());
        cours1.setNom("Nom");
        cours1.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        when(this.coursRepository.save((Cours) any())).thenReturn(cours1);
        when(this.coursRepository.findCoursByNom((String) any())).thenReturn(ofResult);
        assertThrows(GroupeTpDejaAjouterException.class,
                () -> this.coursService.addGroupeTPAuCours("Nom Cours", "Numero Groupe"));
        verify(this.groupeTpRepository).findByNumeroGroupe((String) any());
        verify(this.coursRepository).findCoursByNom((String) any());
    }

    /**
     * Method under test: {@link CoursService#addGroupeTPAuCours(String, String)}
     */
    @Test
    void testAddGroupeTPAuCours4()
            throws CoursInnexistantException, GroupeInnexistantException, GroupeTpDejaAjouterException {
        when(this.groupeTpRepository.findByNumeroGroupe((String) any()))
                .thenReturn(Optional.of(new GroupeTp("Numero Groupe")));

        Cours cours = new Cours();
        cours.setHeureDebut(LocalDate.ofEpochDay(1L));
        cours.setHeureFin(LocalDate.ofEpochDay(1L));
        cours.setId("42");
        cours.setLesGroupes(new HashSet<>());
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        when(this.coursRepository.save((Cours) any())).thenReturn(cours);
        when(this.coursRepository.findCoursByNom((String) any())).thenReturn(Optional.empty());
        assertThrows(CoursInnexistantException.class,
                () -> this.coursService.addGroupeTPAuCours("Nom Cours", "Numero Groupe"));
        verify(this.groupeTpRepository).findByNumeroGroupe((String) any());
        verify(this.coursRepository).findCoursByNom((String) any());
    }

    /**
     * Method under test: {@link CoursService#addProfAuCours(String, String)}
     */
    @Test
    void testAddProfAuCours() throws CoursInnexistantException, ProfDejaAjouterException, ProfInnexistantExcepton {
        when(this.profRepository.findByNom((String) any()))
                .thenReturn(Optional.of(new Prof("42", "Nom", "Prenom", new HashSet<>())));

        Cours cours = new Cours();
        cours.setHeureDebut(LocalDate.ofEpochDay(1L));
        cours.setHeureFin(LocalDate.ofEpochDay(1L));
        cours.setId("42");
        cours.setLesGroupes(new HashSet<>());
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        Optional<Cours> ofResult = Optional.of(cours);
        when(this.coursRepository.findCoursByNom((String) any())).thenReturn(ofResult);
        assertThrows(ProfDejaAjouterException.class, () -> this.coursService.addProfAuCours("Nom Cours", "Nom Prof"));
        verify(this.profRepository).findByNom((String) any());
        verify(this.coursRepository).findCoursByNom((String) any());
    }

    /**
     * Method under test: {@link CoursService#addProfAuCours(String, String)}
     */
    @Test
    void testAddProfAuCours2() throws CoursInnexistantException, ProfDejaAjouterException, ProfInnexistantExcepton {
        when(this.profRepository.save((Prof) any())).thenReturn(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        when(this.profRepository.findByNom((String) any()))
                .thenReturn(Optional.of(new Prof("42", "com.example.projetsem2qrcode.modele.Prof", "Prenom", new HashSet<>())));

        Cours cours = new Cours();
        cours.setHeureDebut(LocalDate.ofEpochDay(1L));
        cours.setHeureFin(LocalDate.ofEpochDay(1L));
        cours.setId("42");
        cours.setLesGroupes(new HashSet<>());
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        Optional<Cours> ofResult = Optional.of(cours);

        Cours cours1 = new Cours();
        cours1.setHeureDebut(LocalDate.ofEpochDay(1L));
        cours1.setHeureFin(LocalDate.ofEpochDay(1L));
        cours1.setId("42");
        cours1.setLesGroupes(new HashSet<>());
        cours1.setNom("Nom");
        cours1.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        when(this.coursRepository.save((Cours) any())).thenReturn(cours1);
        when(this.coursRepository.findCoursByNom((String) any())).thenReturn(ofResult);
        assertSame(cours1, this.coursService.addProfAuCours("Nom Cours", "Nom Prof"));
        verify(this.profRepository).save((Prof) any());
        verify(this.profRepository).findByNom((String) any());
        verify(this.coursRepository).save((Cours) any());
        verify(this.coursRepository).findCoursByNom((String) any());
    }

    /**
     * Method under test: {@link CoursService#addProfAuCours(String, String)}
     */
    @Test
    void testAddProfAuCours3() throws CoursInnexistantException, ProfDejaAjouterException, ProfInnexistantExcepton {

        Cours cours = new Cours();
        cours.setHeureDebut(LocalDate.ofEpochDay(1L));
        cours.setHeureFin(LocalDate.ofEpochDay(1L));
        cours.setId("42");
        cours.setLesGroupes(new HashSet<>());
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        Optional<Cours> ofResult = Optional.of(cours);

        Cours cours1 = new Cours();
        cours1.setHeureDebut(LocalDate.ofEpochDay(1L));
        cours1.setHeureFin(LocalDate.ofEpochDay(1L));
        cours1.setId("42");
        cours1.setLesGroupes(new HashSet<>());
        cours1.setNom("Nom");
        cours1.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));

        when(this.profRepository.findByNom((String) any())).thenReturn(Optional.empty());
        when(this.coursRepository.findCoursByNom((String) any())).thenReturn(ofResult);

        assertThrows(ProfInnexistantExcepton.class, () -> this.coursService.addProfAuCours("Nom Cours", "Nom Prof"));
        verify(this.profRepository).findByNom((String) any());
        verify(this.coursRepository).findCoursByNom((String) any());
    }

    /**
     * Method under test: {@link CoursService#addProfAuCours(String, String)}
     */
    @Test
    void testAddProfAuCours4() throws CoursInnexistantException, ProfDejaAjouterException, ProfInnexistantExcepton {

        Cours cours = mock(Cours.class);
        cours.setHeureDebut(LocalDate.ofEpochDay(1L));
        cours.setHeureFin(LocalDate.ofEpochDay(1L));
        cours.setId("42");
        cours.setLesGroupes(new HashSet<>());
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        Optional<Cours> ofResult = Optional.of(cours);

        Cours cours1 = new Cours();
        cours1.setHeureDebut(LocalDate.ofEpochDay(1L));
        cours1.setHeureFin(LocalDate.ofEpochDay(1L));
        cours1.setId("42");
        cours1.setLesGroupes(new HashSet<>());
        cours1.setNom("Nom");
        cours1.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));

        when(this.profRepository.findByNom((String) any())).thenReturn(Optional.of(new Prof("42", "com.example.projetsem2qrcode.modele.Prof", "Prenom", new HashSet<>())));

        assertThrows(CoursInnexistantException.class, () -> this.coursService.addProfAuCours("Nom Cours", "Nom Prof"));
    }


}