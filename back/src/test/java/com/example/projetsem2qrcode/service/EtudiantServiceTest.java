package com.example.projetsem2qrcode.service;

import com.example.projetsem2qrcode.exceptions.EtudiantInnexistantException;
import com.example.projetsem2qrcode.exceptions.InformationIncompletException;
import com.example.projetsem2qrcode.exceptions.NumEtudiantDejaPresentException;
import com.example.projetsem2qrcode.exceptions.NumEtudiantNonValideException;
import com.example.projetsem2qrcode.modele.Etudiant;
import com.example.projetsem2qrcode.repository.EtudiantRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {EtudiantService.class})
@ExtendWith(SpringExtension.class)
class EtudiantServiceTest {
    @MockBean
    private EtudiantRepository etudiantRepository;

    @Autowired
    private EtudiantService etudiantService;

    /**
     * Method under test: {@link EtudiantService#saveEtudiant(Etudiant)}
     */
    @Test
    void testSaveEtudiant()
            throws InformationIncompletException, NumEtudiantDejaPresentException, NumEtudiantNonValideException {
        when(this.etudiantRepository.save((Etudiant) any()))
                .thenReturn(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true));
        when(this.etudiantRepository.findEtudiantByNumEtudiant((String) any()))
                .thenReturn(Optional.of(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true)));
        assertThrows(NumEtudiantDejaPresentException.class, () -> this.etudiantService
                .saveEtudiant(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true)));
        verify(this.etudiantRepository).findEtudiantByNumEtudiant((String) any());
    }

    /**
     * Method under test: {@link EtudiantService#saveEtudiant(Etudiant)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveEtudiant2()
            throws InformationIncompletException, NumEtudiantDejaPresentException, NumEtudiantNonValideException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.projetsem2qrcode.service.EtudiantService.saveEtudiant(EtudiantService.java:28)
        //   In order to prevent saveEtudiant(Etudiant)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   saveEtudiant(Etudiant).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.etudiantRepository.save((Etudiant) any()))
                .thenReturn(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true));
        when(this.etudiantRepository.findEtudiantByNumEtudiant((String) any())).thenReturn(null);
        this.etudiantService.saveEtudiant(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true));
    }

    /**
     * Method under test: {@link EtudiantService#saveEtudiant(Etudiant)}
     */
    @Test
    void testSaveEtudiant3()
            throws InformationIncompletException, NumEtudiantDejaPresentException, NumEtudiantNonValideException {
        Etudiant etudiant = new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true);

        when(this.etudiantRepository.save((Etudiant) any())).thenReturn(etudiant);
        when(this.etudiantRepository.findEtudiantByNumEtudiant((String) any())).thenReturn(Optional.empty());
        assertSame(etudiant,
                this.etudiantService.saveEtudiant(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true)));
        verify(this.etudiantRepository).save((Etudiant) any());
        verify(this.etudiantRepository).findEtudiantByNumEtudiant((String) any());
    }

    /**
     * Method under test: {@link EtudiantService#saveEtudiant(Etudiant)}
     */
    @Test
    void testSaveEtudiant4()
            throws InformationIncompletException, NumEtudiantDejaPresentException, NumEtudiantNonValideException {
        when(this.etudiantRepository.save((Etudiant) any()))
                .thenReturn(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true));
        when(this.etudiantRepository.findEtudiantByNumEtudiant((String) any()))
                .thenReturn(Optional.of(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true)));
        assertThrows(InformationIncompletException.class,
                () -> this.etudiantService.saveEtudiant(new Etudiant("42", null, "Prenom", "Num Etudiant", "Groupe Tp", true)));
    }

    /**
     * Method under test: {@link EtudiantService#saveEtudiant(Etudiant)}
     */
    @Test
    void testSaveEtudiant5()
            throws InformationIncompletException, NumEtudiantDejaPresentException, NumEtudiantNonValideException {
        when(this.etudiantRepository.save((Etudiant) any()))
                .thenReturn(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true));
        when(this.etudiantRepository.findEtudiantByNumEtudiant((String) any()))
                .thenReturn(Optional.of(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true)));
        assertThrows(InformationIncompletException.class,
                () -> this.etudiantService.saveEtudiant(new Etudiant("42", "", "Prenom", "Num Etudiant", "Groupe Tp", true)));
    }

    /**
     * Method under test: {@link EtudiantService#saveEtudiant(Etudiant)}
     */
    @Test
    void testSaveEtudiant6()
            throws InformationIncompletException, NumEtudiantDejaPresentException, NumEtudiantNonValideException {
        when(this.etudiantRepository.save((Etudiant) any()))
                .thenReturn(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true));
        when(this.etudiantRepository.findEtudiantByNumEtudiant((String) any()))
                .thenReturn(Optional.of(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true)));
        assertThrows(InformationIncompletException.class,
                () -> this.etudiantService.saveEtudiant(new Etudiant("42", "Nom", null, "Num Etudiant", "Groupe Tp", true)));
    }

    /**
     * Method under test: {@link EtudiantService#saveEtudiant(Etudiant)}
     */
    @Test
    void testSaveEtudiant7()
            throws InformationIncompletException, NumEtudiantDejaPresentException, NumEtudiantNonValideException {
        when(this.etudiantRepository.save((Etudiant) any()))
                .thenReturn(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true));
        when(this.etudiantRepository.findEtudiantByNumEtudiant((String) any()))
                .thenReturn(Optional.of(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true)));
        assertThrows(InformationIncompletException.class,
                () -> this.etudiantService.saveEtudiant(new Etudiant("42", "Nom", "", "Num Etudiant", "Groupe Tp", true)));
    }

    /**
     * Method under test: {@link EtudiantService#saveEtudiant(Etudiant)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveEtudiant8()
            throws InformationIncompletException, NumEtudiantDejaPresentException, NumEtudiantNonValideException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.projetsem2qrcode.service.EtudiantService.saveEtudiant(EtudiantService.java:24)
        //   In order to prevent saveEtudiant(Etudiant)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   saveEtudiant(Etudiant).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.etudiantRepository.save((Etudiant) any()))
                .thenReturn(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true));
        when(this.etudiantRepository.findEtudiantByNumEtudiant((String) any()))
                .thenReturn(Optional.of(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true)));
        this.etudiantService.saveEtudiant(null);
    }

    /**
     * Method under test: {@link EtudiantService#saveEtudiant(Etudiant)}
     */
    @Test
    void testSaveEtudiant9()
            throws InformationIncompletException, NumEtudiantDejaPresentException, NumEtudiantNonValideException {
        when(this.etudiantRepository.save((Etudiant) any()))
                .thenReturn(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true));
        when(this.etudiantRepository.findEtudiantByNumEtudiant((String) any())).thenReturn(Optional.empty());
        assertThrows(NumEtudiantNonValideException.class,
                () -> this.etudiantService.saveEtudiant(new Etudiant("42", "Nom", "Prenom", null, "Groupe Tp", true)));
        verify(this.etudiantRepository).findEtudiantByNumEtudiant((String) any());
    }

    /**
     * Method under test: {@link EtudiantService#saveEtudiant(Etudiant)}
     */
    @Test
    void testSaveEtudiant10()
            throws InformationIncompletException, NumEtudiantDejaPresentException, NumEtudiantNonValideException {
        when(this.etudiantRepository.save((Etudiant) any()))
                .thenReturn(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true));
        when(this.etudiantRepository.findEtudiantByNumEtudiant((String) any())).thenReturn(Optional.empty());
        assertThrows(NumEtudiantNonValideException.class,
                () -> this.etudiantService.saveEtudiant(new Etudiant("42", "Nom", "Prenom", "", "Groupe Tp", true)));
        verify(this.etudiantRepository).findEtudiantByNumEtudiant((String) any());
    }

    /**
     * Method under test: {@link EtudiantService#findByNumEtudiant(String)}
     */
    @Test
    void testFindByNumEtudiant() throws EtudiantInnexistantException {
        Etudiant etudiant = new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true);

        when(this.etudiantRepository.findEtudiantByNumEtudiant((String) any())).thenReturn(Optional.of(etudiant));
        assertSame(etudiant, this.etudiantService.findByNumEtudiant("Num Etudiant"));
        verify(this.etudiantRepository).findEtudiantByNumEtudiant((String) any());
    }

    /**
     * Method under test: {@link EtudiantService#findByNumEtudiant(String)}
     */
    @Test
    void testFindByNumEtudiant2() throws EtudiantInnexistantException {
        when(this.etudiantRepository.findEtudiantByNumEtudiant((String) any())).thenReturn(Optional.empty());
        assertThrows(EtudiantInnexistantException.class, () -> this.etudiantService.findByNumEtudiant("Num Etudiant"));
        verify(this.etudiantRepository).findEtudiantByNumEtudiant((String) any());
    }

    /**
     * Method under test: {@link EtudiantService#deleteAllEtudiant()}
     */
    @Test
    void testDeleteAllEtudiant() {
        doNothing().when(this.etudiantRepository).deleteAll();
        this.etudiantService.deleteAllEtudiant();
        verify(this.etudiantRepository).deleteAll();
    }

    /**
     * Method under test: {@link EtudiantService#deleteEtudiantByNumEtudiant(String)}
     */
    @Test
    void testDeleteEtudiantByNumEtudiant() throws EtudiantInnexistantException {
        doNothing().when(this.etudiantRepository).deleteById((String) any());
        when(this.etudiantRepository.findEtudiantByNumEtudiant((String) any()))
                .thenReturn(Optional.of(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true)));
        assertThrows(EtudiantInnexistantException.class,
                () -> this.etudiantService.deleteEtudiantByNumEtudiant("Num Etudiant"));
        verify(this.etudiantRepository).findEtudiantByNumEtudiant((String) any());
        verify(this.etudiantRepository).deleteById((String) any());
    }

    /**
     * Method under test: {@link EtudiantService#deleteEtudiantByNumEtudiant(String)}
     */
    @Test
    void testDeleteEtudiantByNumEtudiant2() throws EtudiantInnexistantException {
        doNothing().when(this.etudiantRepository).deleteById((String) any());
        when(this.etudiantRepository.findEtudiantByNumEtudiant((String) any())).thenReturn(Optional.empty());
        assertThrows(EtudiantInnexistantException.class,
                () -> this.etudiantService.deleteEtudiantByNumEtudiant("Num Etudiant"));
        verify(this.etudiantRepository).findEtudiantByNumEtudiant((String) any());
    }

    /**
     * Method under test: {@link EtudiantService#updateEtudiantByNumEtudiant(String, Etudiant)}
     */
    @Test
    void testUpdateEtudiantByNumEtudiant() throws EtudiantInnexistantException {
        Etudiant etudiant = new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true);

        when(this.etudiantRepository.save((Etudiant) any())).thenReturn(etudiant);
        when(this.etudiantRepository.findEtudiantByNumEtudiant((String) any()))
                .thenReturn(Optional.of(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true)));
        assertSame(etudiant, this.etudiantService.updateEtudiantByNumEtudiant("Num Etudiant",
                new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true)));
        verify(this.etudiantRepository).save((Etudiant) any());
        verify(this.etudiantRepository).findEtudiantByNumEtudiant((String) any());
    }

    /**
     * Method under test: {@link EtudiantService#updateEtudiantByNumEtudiant(String, Etudiant)}
     */
    @Test
    void testUpdateEtudiantByNumEtudiant2() throws EtudiantInnexistantException {
        when(this.etudiantRepository.save((Etudiant) any()))
                .thenReturn(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true));
        when(this.etudiantRepository.findEtudiantByNumEtudiant((String) any())).thenReturn(Optional.empty());
        assertThrows(EtudiantInnexistantException.class,
                () -> this.etudiantService.updateEtudiantByNumEtudiant("Num Etudiant",
                        new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true)));
        verify(this.etudiantRepository).findEtudiantByNumEtudiant((String) any());
    }

    /**
     * Method under test: {@link EtudiantService#updateEtudiantByNumEtudiant(String, Etudiant)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateEtudiantByNumEtudiant3() throws EtudiantInnexistantException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.projetsem2qrcode.service.EtudiantService.updateEtudiantByNumEtudiant(EtudiantService.java:62)
        //   In order to prevent updateEtudiantByNumEtudiant(String, Etudiant)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updateEtudiantByNumEtudiant(String, Etudiant).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.etudiantRepository.save((Etudiant) any()))
                .thenReturn(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true));
        when(this.etudiantRepository.findEtudiantByNumEtudiant((String) any()))
                .thenReturn(Optional.of(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true)));
        this.etudiantService.updateEtudiantByNumEtudiant("Num Etudiant", null);
    }

    /**
     * Method under test: {@link EtudiantService#getAllEtudiant()}
     */
    @Test
    void testGetAllEtudiant() {
        ArrayList<Etudiant> etudiantList = new ArrayList<>();
        when(this.etudiantRepository.findAll()).thenReturn(etudiantList);
        List<Etudiant> actualAllEtudiant = this.etudiantService.getAllEtudiant();
        assertSame(etudiantList, actualAllEtudiant);
        assertTrue(actualAllEtudiant.isEmpty());
        verify(this.etudiantRepository).findAll();
    }

    /**
     * Method under test: {@link EtudiantService#reinitEmargement()}
     */
    @Test
    void testReinitEmargement() {
        when(this.etudiantRepository.findAll()).thenReturn(new ArrayList<>());
        this.etudiantService.reinitEmargement();
        verify(this.etudiantRepository).findAll();
    }

    /**
     * Method under test: {@link EtudiantService#reinitEmargement()}
     */
    @Test
    void testReinitEmargement2() {
        ArrayList<Etudiant> etudiantList = new ArrayList<>();
        etudiantList.add(new Etudiant("42", "Nom", "Prenom", "Num Etudiant", "Groupe Tp", true));
        when(this.etudiantRepository.findAll()).thenReturn(etudiantList);
        this.etudiantService.reinitEmargement();
        verify(this.etudiantRepository).findAll();
    }

    /**
     * Method under test: {@link EtudiantService#reinitEmargement()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testReinitEmargement3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.projetsem2qrcode.service.EtudiantService.reinitEmargement(EtudiantService.java:78)
        //   In order to prevent reinitEmargement()
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   reinitEmargement().
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Etudiant> etudiantList = new ArrayList<>();
        etudiantList.add(null);
        when(this.etudiantRepository.findAll()).thenReturn(etudiantList);
        this.etudiantService.reinitEmargement();
    }
}

