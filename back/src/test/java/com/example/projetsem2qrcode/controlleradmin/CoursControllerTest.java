package com.example.projetsem2qrcode.controlleradmin;

import com.example.projetsem2qrcode.modele.Cours;
import com.example.projetsem2qrcode.modele.GroupeTp;
import com.example.projetsem2qrcode.modele.Prof;
import com.example.projetsem2qrcode.service.CoursService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.HashSet;

import static org.mockito.Mockito.*;

class CoursControllerTest {

    /**
     * Method under test: {@link CoursController#createCours(Cours)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateCours() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.projetsem2qrcode.controlleradmin.CoursController.createCours(CoursController.java:26)
        //   In order to prevent createCours(Cours)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   createCours(Cours).
        //   See https://diff.blue/R013 to resolve this issue.

        CoursController coursController = new CoursController();

        Cours cours = new Cours();
        cours.setHeureDebut(null);
        cours.setHeureFin(null);
        cours.setId("42");
        cours.setLesGroupes(new HashSet<>());
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        coursController.createCours(cours);
    }

    /**
     * Method under test: {@link CoursController#createCours(Cours)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateCours2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.projetsem2qrcode.controlleradmin.CoursController.createCours(CoursController.java:26)
        //   In order to prevent createCours(Cours)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   createCours(Cours).
        //   See https://diff.blue/R013 to resolve this issue.

        CoursController coursController = new CoursController();
        Cours cours = mock(Cours.class);
        doNothing().when(cours).setHeureDebut((LocalDate) any());
        doNothing().when(cours).setHeureFin((LocalDate) any());
        doNothing().when(cours).setId((String) any());
        doNothing().when(cours).setLesGroupes((java.util.Set<GroupeTp>) any());
        doNothing().when(cours).setNom((String) any());
        doNothing().when(cours).setProf((Prof) any());
        cours.setHeureDebut(null);
        cours.setHeureFin(null);
        cours.setId("42");
        cours.setLesGroupes(new HashSet<>());
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        coursController.createCours(cours);
    }

    @Autowired
    private CoursController coursController;

    @MockBean
    private CoursService coursService;


}