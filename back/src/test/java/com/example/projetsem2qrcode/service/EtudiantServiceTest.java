package com.example.projetsem2qrcode.service;

import com.example.projetsem2qrcode.data.DataTestImpl;
import com.example.projetsem2qrcode.exceptions.EtudiantInnexistantException;
import com.example.projetsem2qrcode.exceptions.InformationIncompletException;
import com.example.projetsem2qrcode.exceptions.NumEtudiantDejaPresentException;
import com.example.projetsem2qrcode.exceptions.NumEtudiantNonValideException;
import com.example.projetsem2qrcode.modele.Etudiant;
import com.example.projetsem2qrcode.repository.EtudiantRepository;
import org.junit.jupiter.api.BeforeEach;
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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {EtudiantService.class})
@ExtendWith(SpringExtension.class)
class EtudiantServiceTest {
    @MockBean
    private EtudiantRepository etudiantRepository;

    @Autowired
    private EtudiantService etudiantService;

    private DataTestImpl dataTest = new DataTestImpl();

    @BeforeEach
    void init() {
        dataTest = new DataTestImpl();
        etudiantService = new EtudiantService(etudiantRepository);
    }

    /**
     * Method under test: {@link EtudiantService#saveEtudiant(Etudiant)}
     */
    @Test
    void testSaveEtudiantKo1()
            throws InformationIncompletException, NumEtudiantDejaPresentException, NumEtudiantNonValideException {
        // Given
        String id = dataTest.idBase();
        String nom = dataTest.nomEtudiantBase();
        String prenom = dataTest.prenomEtudiant();
        String numEtudiant = dataTest.numEtudiant();
        String groupeTp = dataTest.groupTp();
        boolean emargement = dataTest.emargementEtudiantFalse();
        Etudiant etudiant = new Etudiant(id, nom, prenom, numEtudiant, groupeTp, emargement);

        // When
        when(this.etudiantRepository.findEtudiantByNumEtudiant(numEtudiant))
                .thenReturn(Optional.of(etudiant));
        // Assert
        assertThrows(NumEtudiantDejaPresentException.class, () -> this.etudiantService
                .saveEtudiant(etudiant));
        verify(this.etudiantRepository).findEtudiantByNumEtudiant(numEtudiant);
    }

    /**
     * Method under test: {@link EtudiantService#saveEtudiant(Etudiant)}
     * @throws InformationIncompletException   si les informations nom ou prénom son incomplet (blanc ou null)
     * @throws NumEtudiantDejaPresentException si le numéro étudiant est deja pris dans la base de donnée
     * @throws NumEtudiantNonValideException   si le numéro étudiant est incomplet (blanc ou null)
     */
    @Test
    void testSaveEtudiantOk() throws InformationIncompletException, NumEtudiantDejaPresentException, NumEtudiantNonValideException {
        // Given
        String id = dataTest.idBase();
        String nom = dataTest.nomEtudiantBase();
        String prenom = dataTest.prenomEtudiant();
        String numEtudiant = dataTest.numEtudiant();
        String groupeTp = dataTest.groupTp();
        boolean emargement = dataTest.emargementEtudiantFalse();
        Etudiant etudiant = new Etudiant(id, nom, prenom, numEtudiant, groupeTp, emargement);

        // When
        when(this.etudiantRepository.save(etudiant)).thenReturn(etudiant);
        when(this.etudiantRepository.findEtudiantByNumEtudiant(numEtudiant)).thenReturn(Optional.empty());

        // Assert
        assertNotNull(etudiantService.saveEtudiant(etudiant));
        verify(this.etudiantRepository).findEtudiantByNumEtudiant(numEtudiant);
    }

    /**
     * Method under test: {@link EtudiantService#saveEtudiant(Etudiant)}
     * Avec un numéro étudiant null
     */
    @Test
    void testSaveEtudiantKo2() {
        // Given
        String id = dataTest.idBase();
        String nom = dataTest.nomEtudiantBase();
        String prenom = dataTest.prenomEtudiant();
        String numEtudiant = dataTest.numEtudiantNull();
        String groupeTp = dataTest.groupTp();
        boolean emargement = dataTest.emargementEtudiantFalse();
        Etudiant etudiant = new Etudiant(id, nom, prenom, numEtudiant, groupeTp, emargement);

        // When
        when(this.etudiantRepository.findEtudiantByNumEtudiant(numEtudiant)).thenReturn(Optional.empty());

        // Assert
        assertThrows(NumEtudiantNonValideException.class, () -> this.etudiantService.saveEtudiant(etudiant));
        verify(this.etudiantRepository).findEtudiantByNumEtudiant(numEtudiant);
    }

    /**
     * Method under test: {@link EtudiantService#saveEtudiant(Etudiant)}
     * Avec un numéro étudiant blanc
     */
    @Test
    void testSaveEtudiantKo3() {
        // Given
        String id = dataTest.idBase();
        String nom = dataTest.nomEtudiantBase();
        String prenom = dataTest.prenomEtudiant();
        String numEtudiant = dataTest.numEtudiantBlanck();
        String groupeTp = dataTest.groupTp();
        boolean emargement = dataTest.emargementEtudiantFalse();
        Etudiant etudiant = new Etudiant(id, nom, prenom, numEtudiant, groupeTp, emargement);

        // When
        when(this.etudiantRepository.findEtudiantByNumEtudiant(numEtudiant)).thenReturn(Optional.empty());

        // Assert
        assertThrows(NumEtudiantNonValideException.class, () -> this.etudiantService.saveEtudiant(etudiant));
        verify(this.etudiantRepository).findEtudiantByNumEtudiant(numEtudiant);
    }

    /**
     * Method under test: {@link EtudiantService#saveEtudiant(Etudiant)}
     * Avec un numéro nom blanc
     */
    @Test
    void testSaveEtudiantKo4() {
        // Given
        String id = dataTest.idBase();
        String nom = dataTest.nomEtudiantBlanck();
        String prenom = dataTest.prenomEtudiant();
        String numEtudiant = dataTest.numEtudiant();
        String groupeTp = dataTest.groupTp();
        boolean emargement = dataTest.emargementEtudiantFalse();
        Etudiant etudiant = new Etudiant(id, nom, prenom, numEtudiant, groupeTp, emargement);

        // When
        when(this.etudiantRepository.findEtudiantByNumEtudiant(numEtudiant)).thenReturn(Optional.empty());

        // Assert
        assertThrows(InformationIncompletException.class, () -> this.etudiantService.saveEtudiant(etudiant));
    }

    /**
     * Method under test: {@link EtudiantService#saveEtudiant(Etudiant)}
     * Avec un numéro nom null
     */
    @Test
    void testSaveEtudiantKo5() {
        // Given
        String id = dataTest.idBase();
        String nom = dataTest.nomEtudiantNull();
        String prenom = dataTest.prenomEtudiant();
        String numEtudiant = dataTest.numEtudiant();
        String groupeTp = dataTest.groupTp();
        boolean emargement = dataTest.emargementEtudiantFalse();
        Etudiant etudiant = new Etudiant(id, nom, prenom, numEtudiant, groupeTp, emargement);

        // When
        when(this.etudiantRepository.findEtudiantByNumEtudiant(numEtudiant)).thenReturn(Optional.empty());

        // Assert
        assertThrows(InformationIncompletException.class, () -> this.etudiantService.saveEtudiant(etudiant));
    }

    /**
     * Method under test: {@link EtudiantService#saveEtudiant(Etudiant)}
     * Avec un numéro prenom blanc
     */
    @Test
    void testSaveEtudiantKo6() {
        // Given
        String id = dataTest.idBase();
        String nom = dataTest.nomEtudiantBase();
        String prenom = dataTest.prenomEtudiantBlanck();
        String numEtudiant = dataTest.numEtudiant();
        String groupeTp = dataTest.groupTp();
        boolean emargement = dataTest.emargementEtudiantFalse();
        Etudiant etudiant = new Etudiant(id, nom, prenom, numEtudiant, groupeTp, emargement);

        // When
        when(this.etudiantRepository.findEtudiantByNumEtudiant(numEtudiant)).thenReturn(Optional.empty());

        // Assert
        assertThrows(InformationIncompletException.class, () -> this.etudiantService.saveEtudiant(etudiant));
    }

    /**
     * Method under test: {@link EtudiantService#saveEtudiant(Etudiant)}
     * Avec un numéro prenom null
     */
    @Test
    void testSaveEtudiantKo7() {
        // Given
        String id = dataTest.idBase();
        String nom = dataTest.nomEtudiantBase();
        String prenom = dataTest.prenomEtudiantNull();
        String numEtudiant = dataTest.numEtudiant();
        String groupeTp = dataTest.groupTp();
        boolean emargement = dataTest.emargementEtudiantFalse();
        Etudiant etudiant = new Etudiant(id, nom, prenom, numEtudiant, groupeTp, emargement);

        // When
        when(this.etudiantRepository.findEtudiantByNumEtudiant(numEtudiant)).thenReturn(Optional.empty());

        // Assert
        assertThrows(InformationIncompletException.class, () -> this.etudiantService.saveEtudiant(etudiant));
    }

    /**
     * Method under test: {@link EtudiantService#findByNumEtudiant(String)}
     *
     * @throws EtudiantInnexistantException si l'étudiant n'existe pas
     */
    @Test
    void findEtudiantByNumEtudiantOk() throws EtudiantInnexistantException {
        // Given
        String id = dataTest.idBase();
        String nom = dataTest.nomEtudiantBase();
        String prenom = dataTest.prenomEtudiantNull();
        String numEtudiant = dataTest.numEtudiant();
        String groupeTp = dataTest.groupTp();
        boolean emargement = dataTest.emargementEtudiantFalse();
        Etudiant etudiant = new Etudiant(id, nom, prenom, numEtudiant, groupeTp, emargement);

        // When
        when(this.etudiantRepository.findEtudiantByNumEtudiant(numEtudiant)).thenReturn(Optional.of(etudiant));

        // Assert
        assertNotNull(etudiantService.findByNumEtudiant(numEtudiant));
        verify(this.etudiantRepository).findEtudiantByNumEtudiant(numEtudiant);
    }

    /**
     * Method under test: {@link EtudiantService#findByNumEtudiant(String)}
     */
    @Test
    void findEtudiantByNumEtudiantKo() {
        // Given
        String id = dataTest.idBase();
        String nom = dataTest.nomEtudiantBase();
        String prenom = dataTest.prenomEtudiantNull();
        String numEtudiant = dataTest.numEtudiant();
        String groupeTp = dataTest.groupTp();
        boolean emargement = dataTest.emargementEtudiantFalse();
        Etudiant etudiant = new Etudiant(id, nom, prenom, numEtudiant, groupeTp, emargement);

        // When
        when(this.etudiantRepository.findEtudiantByNumEtudiant(numEtudiant)).thenReturn(Optional.empty());

        // Assert
        assertThrows(EtudiantInnexistantException.class, () -> etudiantService.findByNumEtudiant(numEtudiant));
        verify(this.etudiantRepository).findEtudiantByNumEtudiant(numEtudiant);
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
     * Methode under test : {@link EtudiantService#deleteEtudiantByNumEtudiant(String)}
     *
     * @throws EtudiantInnexistantException il ne trouve pas l'étudiant
     */
    @Test
    void deleteEtudiantByNumEtudiantOK() throws EtudiantInnexistantException {
        // Given
        String id = dataTest.idBase();
        String nom = dataTest.nomEtudiantBase();
        String prenom = dataTest.prenomEtudiantNull();
        String numEtudiant = dataTest.numEtudiant();
        String groupeTp = dataTest.groupTp();
        boolean emargement = dataTest.emargementEtudiantFalse();
        Etudiant etudiant = new Etudiant(id, nom, prenom, numEtudiant, groupeTp, emargement);

        // When
        doNothing().when(this.etudiantRepository).delete(etudiant);
        when(etudiantRepository.findEtudiantByNumEtudiant(numEtudiant)).thenReturn(Optional.of(etudiant));

        // Assert
        assertThrows(EtudiantInnexistantException.class, () -> this.etudiantService.deleteEtudiantByNumEtudiant(numEtudiant));
        verify(this.etudiantRepository).findEtudiantByNumEtudiant(numEtudiant);
        verify(this.etudiantRepository).deleteById(id);
    }

    /**
     * Method under test: {@link EtudiantService#deleteEtudiantByNumEtudiant(String)}
     */
    @Test
    void testDeleteEtudiantByNumEtudiantKo() {
        // Given
        String id = dataTest.idBase();
        String numEtudiant = dataTest.numEtudiant();

        // When
        doNothing().when(this.etudiantRepository).deleteById(id);
        when(this.etudiantRepository.findEtudiantByNumEtudiant(numEtudiant)).thenReturn(Optional.empty());

        // Assert
        assertThrows(EtudiantInnexistantException.class, () -> this.etudiantService.deleteEtudiantByNumEtudiant(numEtudiant));
        verify(this.etudiantRepository).findEtudiantByNumEtudiant(numEtudiant);
    }

    /**
     * Method under test: {@link EtudiantService#getAllEtudiant()}
     */
    @Test
    void testGetAllEtudiant() {
        // Given
        ArrayList<Etudiant> etudiantList = new ArrayList<>();

        // When
        when(this.etudiantRepository.findAll()).thenReturn(etudiantList);

        // Assert
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
        // Given
        String id = dataTest.idBase();
        String nom = dataTest.nomEtudiantBase();
        String prenom = dataTest.prenomEtudiantNull();
        String numEtudiant = dataTest.numEtudiant();
        String groupeTp = dataTest.groupTp();
        boolean emargement = dataTest.emargementEtudiantTrue();
        Etudiant etudiant = new Etudiant(id, nom, prenom, numEtudiant, groupeTp, emargement);
        ArrayList<Etudiant> etudiantList = new ArrayList<>();

        //When
        when(this.etudiantRepository.findAll()).thenReturn(etudiantList);

        // Assert
        etudiantList.add(etudiant);
        this.etudiantService.reinitEmargement();
        verify(this.etudiantRepository).findAll();
    }

    /**
     * Method under test: {@link EtudiantService#updateEtudiantByNumEtudiant(String, Etudiant)}
     * @throws EtudiantInnexistantException si l'étudiant n'existe pas
     */
    @Test
    void updateEtudiantOk() throws EtudiantInnexistantException {
        // Given
        String id = dataTest.idBase();
        String nom = dataTest.nomEtudiantBase();
        String prenom = dataTest.prenomEtudiantNull();
        String numEtudiant = dataTest.numEtudiant();
        String groupeTp = dataTest.groupTp();
        boolean emargement = dataTest.emargementEtudiantTrue();

        String id2 = dataTest.idBase();
        String nom2 = "wedat";
        String prenom2 = "balt";
        String numEtudiant2 = "2215442";
        String groupeTp2 = "tp2";
        boolean emargement2 = dataTest.emargementEtudiantTrue();

        Etudiant etudiant = new Etudiant(id, nom, prenom, numEtudiant, groupeTp, emargement);
        Optional<Etudiant> ofResult = Optional.of(etudiant);
        Etudiant etudiant1 = new Etudiant(id, nom, prenom, numEtudiant, groupeTp, emargement);
        Etudiant etudiant2 = new Etudiant(id2, nom2, prenom2, numEtudiant2, groupeTp2, emargement2);

        // When
        when(this.etudiantRepository.save((Etudiant) any())).thenReturn(etudiant1);
        when(this.etudiantRepository.findEtudiantByNumEtudiant(numEtudiant)).thenReturn(ofResult);

        // Assert
        assertSame(etudiant1, etudiantService.updateEtudiantByNumEtudiant(numEtudiant,etudiant2));
        verify(etudiantRepository).findEtudiantByNumEtudiant(numEtudiant);
        verify(this.etudiantRepository).save((Etudiant) any());
    }

    /**
     * Method under test: {@link EtudiantService#updateEtudiantByNumEtudiant(String, Etudiant)}
     */
    @Test
    void updateEtudiantKo(){
        // Given
        String id = dataTest.idBase();
        String nom = dataTest.nomEtudiantBase();
        String prenom = dataTest.prenomEtudiantNull();
        String numEtudiant = dataTest.numEtudiant();
        String groupeTp = dataTest.groupTp();
        boolean emargement = dataTest.emargementEtudiantTrue();

        Etudiant etudiant = new Etudiant(id, nom, prenom, numEtudiant, groupeTp, emargement);
        Optional<Etudiant> ofResult = Optional.of(etudiant);
        Etudiant etudiant1 = new Etudiant(id, nom, prenom, numEtudiant, groupeTp, emargement);

        // When
        when(this.etudiantRepository.save((Etudiant) any())).thenReturn(etudiant1);
        when(this.etudiantRepository.findEtudiantByNumEtudiant(numEtudiant)).thenReturn(Optional.empty());

        // Assert
        assertThrows(EtudiantInnexistantException.class, () -> this.etudiantService.updateEtudiantByNumEtudiant(numEtudiant, etudiant1));
        verify(etudiantRepository).findEtudiantByNumEtudiant(numEtudiant);
    }
}