package com.example.projetsem2qrcode.service;

import com.example.projetsem2qrcode.exceptions.*;
import com.example.projetsem2qrcode.modele.Etudiant;
import com.example.projetsem2qrcode.modele.GroupeTp;
import com.example.projetsem2qrcode.repository.EtudiantRepository;
import com.example.projetsem2qrcode.repository.GroupeTpRepository;
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
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {GroupeTpService.class})
@ExtendWith(SpringExtension.class)
class GroupeTpServiceTest {

    @Autowired
    private GroupeTpService groupeTpService;
    @MockBean
    private GroupeTpRepository groupeTpRepository;

    @MockBean
    private EtudiantRepository etudiantRepository;

    @BeforeEach
    void init() {
        groupeTpService = new GroupeTpService(groupeTpRepository, etudiantRepository);
    }

    /**
     * Method under test: {@link GroupeTpService#saveGroupeTp(GroupeTp)}
     */
    @Test
    void testSaveGroupeTpKo1() throws GroupeDejaCreerException, NomGroupeNonValideException {
        //when
        when(this.groupeTpRepository.save((GroupeTp) any())).thenReturn(new GroupeTp("Numero Groupe"));
        when(this.groupeTpRepository.findByNumeroGroupe((String) any()))
                .thenReturn(Optional.of(new GroupeTp("Numero Groupe")));
        //assert
        assertThrows(GroupeDejaCreerException.class,
                () -> this.groupeTpService.saveGroupeTp(new GroupeTp("Numero Groupe")));
        verify(this.groupeTpRepository).findByNumeroGroupe((String) any());
    }

    /**
     * Method under test: {@link GroupeTpService#saveGroupeTp(GroupeTp)}
     */
    @Test
    void testSaveGroupeTpOk() throws GroupeDejaCreerException, NomGroupeNonValideException {
        //given
        GroupeTp groupeTp = new GroupeTp("Numero Groupe");
        //when
        when(this.groupeTpRepository.save((GroupeTp) any())).thenReturn(groupeTp);
        when(this.groupeTpRepository.findByNumeroGroupe((String) any())).thenReturn(Optional.empty());
        //assert
        assertSame(groupeTp, this.groupeTpService.saveGroupeTp(new GroupeTp("Numero Groupe")));
        verify(this.groupeTpRepository).save((GroupeTp) any());
        verify(this.groupeTpRepository).findByNumeroGroupe((String) any());
    }

    /**
     * Method under test: {@link GroupeTpService#saveGroupeTp(GroupeTp)}
     */
    @Test
    void testSaveGroupeTpKo2() throws GroupeDejaCreerException, NomGroupeNonValideException {
        //when
        when(this.groupeTpRepository.save((GroupeTp) any())).thenReturn(new GroupeTp(null));
        when(this.groupeTpRepository.findByNumeroGroupe((String) any())).thenReturn(Optional.empty());
        //assert
        assertThrows(NomGroupeNonValideException.class, () -> this.groupeTpService.saveGroupeTp(new GroupeTp(null)));
        verify(this.groupeTpRepository).findByNumeroGroupe((String) any());
    }

    /**
     * Method under test: {@link GroupeTpService#saveGroupeTp(GroupeTp)}
     */
    @Test
    void testSaveGroupeTpKo3() throws GroupeDejaCreerException, NomGroupeNonValideException {
        //when
        when(this.groupeTpRepository.save((GroupeTp) any())).thenReturn(new GroupeTp("  "));
        when(this.groupeTpRepository.findByNumeroGroupe((String) any())).thenReturn(Optional.empty());
        //assert
        assertThrows(NomGroupeNonValideException.class, () -> this.groupeTpService.saveGroupeTp(new GroupeTp(null)));
        verify(this.groupeTpRepository).findByNumeroGroupe((String) any());
    }

    /**
     * Method under test: {@link GroupeTpService#saveGroupeTp(GroupeTp)}
     */
    @Test
    void testSaveGroupeTpKo4() throws GroupeDejaCreerException, NomGroupeNonValideException {
        //when
        when(this.groupeTpRepository.save((GroupeTp) any())).thenReturn(new GroupeTp("Numero Groupe"));
        when(this.groupeTpRepository.findByNumeroGroupe((String) any())).thenReturn(Optional.empty());
        //assert
        assertThrows(NomGroupeNonValideException.class, () -> this.groupeTpService.saveGroupeTp(new GroupeTp("")));
        verify(this.groupeTpRepository).findByNumeroGroupe((String) any());
    }

    /**
     * Method under test: {@link GroupeTpService#findGroupeByNumGroupe(String)}
     */
    @Test
    void testFindGroupeByNumGroupeOk() throws GroupeInnexistantException {
        //given
        GroupeTp groupeTp = new GroupeTp("Numero Groupe");
        //when
        when(this.groupeTpRepository.findByNumeroGroupe((String) any())).thenReturn(Optional.of(groupeTp));
        //assert
        assertSame(groupeTp, this.groupeTpService.findGroupeByNumGroupe("Numero Groupe"));
        verify(this.groupeTpRepository).findByNumeroGroupe((String) any());
    }

    /**
     * Method under test: {@link GroupeTpService#findGroupeByNumGroupe(String)}
     */
    @Test
    void testFindGroupeByNumGroupeKo1() throws GroupeInnexistantException {
        //when
        when(this.groupeTpRepository.findByNumeroGroupe((String) any())).thenReturn(Optional.empty());
        //assert
        assertThrows(GroupeInnexistantException.class, () -> this.groupeTpService.findGroupeByNumGroupe("Numero Groupe"));
        verify(this.groupeTpRepository).findByNumeroGroupe((String) any());
    }

    /**
     * Method under test: {@link GroupeTpService#getAllGroupeTp()}
     */
    @Test
    void testGetAllGroupeTp() {
        //given
        ArrayList<GroupeTp> groupeTpList = new ArrayList<>();
        //when
        when(this.groupeTpRepository.findAll()).thenReturn(groupeTpList);
        List<GroupeTp> actualAllGroupeTp = this.groupeTpService.getAllGroupeTp();
        //assert
        assertSame(groupeTpList, actualAllGroupeTp);
        assertTrue(actualAllGroupeTp.isEmpty());
        verify(this.groupeTpRepository).findAll();
    }

    /**
     * Method under test: {@link GroupeTpService#deleteAllGroupeTp()}
     */
    @Test
    void testDeleteAllGroupeTp() {
        //when
        doNothing().when(this.groupeTpRepository).deleteAll();
        this.groupeTpService.deleteAllGroupeTp();
        //assert
        verify(this.groupeTpRepository).deleteAll();
    }

    /**
     * Method under test: {@link GroupeTpService#deleteGroupeByNumGroupe(String)}
     */

    //a verifier car que exception
    @Test
    void testDeleteGroupeByNumGroupe() throws GroupeInnexistantException {
        //when
        doNothing().when(this.groupeTpRepository).deleteById((String) any());
        when(this.groupeTpRepository.findByNumeroGroupe((String) any()))
                .thenReturn(Optional.of(new GroupeTp("Numero Groupe")));
        //assert
        assertThrows(GroupeInnexistantException.class, () -> this.groupeTpService.deleteGroupeByNumGroupe("Numero Groupe"));
        verify(this.groupeTpRepository).findByNumeroGroupe((String) any());
        verify(this.groupeTpRepository).deleteById((String) any());
    }

    /**
     * Method under test: {@link GroupeTpService#deleteGroupeByNumGroupe(String)}
     */

    //a verifier car que exception
    @Test
    void testDeleteGroupeByNumGroupe2() throws GroupeInnexistantException {
        //when
        doNothing().when(this.groupeTpRepository).deleteById((String) any());
        when(this.groupeTpRepository.findByNumeroGroupe((String) any())).thenReturn(Optional.empty());
        //assert
        assertThrows(GroupeInnexistantException.class, () -> this.groupeTpService.deleteGroupeByNumGroupe("Numero Groupe"));
        verify(this.groupeTpRepository).findByNumeroGroupe((String) any());
    }

    /**
     * Method under test: {@link GroupeTpService#addEtudiantInGroupe(String, String)}
     */
    @Test
    void testAddEtudiantInGroupeOk()
            throws EtudiantDejaDansUnGroupeException, EtudiantInnexistantException, GroupeInnexistantException {
        //when
        GroupeTp groupeTp = new GroupeTp("Numero Groupe");
        when(this.groupeTpRepository.save((GroupeTp) any())).thenReturn(groupeTp);
        when(this.groupeTpRepository.findByNumeroGroupe((String) any()))
                .thenReturn(Optional.of(new GroupeTp("Numero Groupe")));

        //given
        Etudiant etudiant = new Etudiant();
        etudiant.setEmargement(true);
        etudiant.setGroupeTp("Groupe Tp");
        etudiant.setId("42");
        etudiant.setNom("Nom");
        etudiant.setNumEtudiant("Num Etudiant");
        etudiant.setPrenom("Prenom");
        Optional<Etudiant> ofResult = Optional.of(etudiant);

        Etudiant etudiant1 = new Etudiant();
        etudiant1.setEmargement(true);
        etudiant1.setGroupeTp("Groupe Tp");
        etudiant1.setId("42");
        etudiant1.setNom("Nom");
        etudiant1.setNumEtudiant("Num Etudiant");
        etudiant1.setPrenom("Prenom");
        //when
        when(this.etudiantRepository.save((Etudiant) any())).thenReturn(etudiant1);
        when(this.etudiantRepository.findEtudiantByNumEtudiant((String) any())).thenReturn(ofResult);
        //assert
        assertSame(groupeTp, this.groupeTpService.addEtudiantInGroupe("Groupe", "Num Etudiant"));
        verify(this.groupeTpRepository).save((GroupeTp) any());
        verify(this.groupeTpRepository).findByNumeroGroupe((String) any());
        verify(this.etudiantRepository).save((Etudiant) any());
        verify(this.etudiantRepository).findEtudiantByNumEtudiant((String) any());
    }

    /**
     * Method under test: {@link GroupeTpService#addEtudiantInGroupe(String, String)}
     */
    @Test
    void testAddEtudiantInGroupeKo1()
            throws EtudiantDejaDansUnGroupeException, EtudiantInnexistantException, GroupeInnexistantException {

        //given
        Etudiant etudiant = new Etudiant();
        etudiant.setEmargement(true);
        etudiant.setGroupeTp("Groupe Tp");
        etudiant.setId("42");
        etudiant.setNom("Nom");
        etudiant.setNumEtudiant("Num Etudiant");
        etudiant.setPrenom("Prenom");
        Optional<Etudiant> ofResult = Optional.of(etudiant);

        Etudiant etudiant1 = new Etudiant();
        etudiant1.setEmargement(true);
        etudiant1.setGroupeTp("Groupe Tp");
        etudiant1.setId("42");
        etudiant1.setNom("Nom");
        etudiant1.setNumEtudiant("Num Etudiant");
        etudiant1.setPrenom("Prenom");

        GroupeTp groupeTp = new GroupeTp("Nom Groupe");
        groupeTp.getListeEtudiantGroupe().add(etudiant);
        //when
        when(this.groupeTpRepository.save((GroupeTp) any())).thenReturn(groupeTp);
        when(this.groupeTpRepository.findByNumeroGroupe((String) any())).thenReturn(Optional.of(groupeTp));
        when(this.etudiantRepository.save((Etudiant) any())).thenReturn(etudiant1);
        when(this.etudiantRepository.findEtudiantByNumEtudiant((String) any())).thenReturn(ofResult);
        //assert
        assertThrows(EtudiantDejaDansUnGroupeException.class, () -> this.groupeTpService.addEtudiantInGroupe("Groupe", "Num Etudiant"));
    }

    /**
     * Method under test: {@link GroupeTpService#addEtudiantInGroupe(String, String)}
     */
    @Test
    void testAddEtudiantInGroupeKo2()
            throws EtudiantDejaDansUnGroupeException, EtudiantInnexistantException, GroupeInnexistantException {
        //when
        when(this.groupeTpRepository.save((GroupeTp) any())).thenReturn(new GroupeTp("Numero Groupe"));
        when(this.groupeTpRepository.findByNumeroGroupe((String) any())).thenReturn(Optional.empty());

        //given
        Etudiant etudiant = new Etudiant();
        etudiant.setEmargement(true);
        etudiant.setGroupeTp("Groupe Tp");
        etudiant.setId("42");
        etudiant.setNom("Nom");
        etudiant.setNumEtudiant("Num Etudiant");
        etudiant.setPrenom("Prenom");
        Optional<Etudiant> ofResult = Optional.of(etudiant);

        Etudiant etudiant1 = new Etudiant();
        etudiant1.setEmargement(true);
        etudiant1.setGroupeTp("Groupe Tp");
        etudiant1.setId("42");
        etudiant1.setNom("Nom");
        etudiant1.setNumEtudiant("Num Etudiant");
        etudiant1.setPrenom("Prenom");
        //when
        when(this.etudiantRepository.save((Etudiant) any())).thenReturn(etudiant1);
        when(this.etudiantRepository.findEtudiantByNumEtudiant((String) any())).thenReturn(ofResult);
        //assert
        assertThrows(GroupeInnexistantException.class,
                () -> this.groupeTpService.addEtudiantInGroupe("Groupe", "Num Etudiant"));
        verify(this.groupeTpRepository).findByNumeroGroupe((String) any());
        verify(this.etudiantRepository).findEtudiantByNumEtudiant((String) any());
    }

    /**
     * Method under test: {@link GroupeTpService#addEtudiantInGroupe(String, String)}
     */
    @Test
    void testAddEtudiantInGroupe4()
            throws EtudiantDejaDansUnGroupeException, EtudiantInnexistantException, GroupeInnexistantException {
        //when
        when(this.groupeTpRepository.save((GroupeTp) any())).thenReturn(new GroupeTp("Numero Groupe"));
        when(this.groupeTpRepository.findByNumeroGroupe((String) any()))
                .thenReturn(Optional.of(new GroupeTp("Numero Groupe")));

        //given
        Etudiant etudiant = new Etudiant();
        etudiant.setEmargement(true);
        etudiant.setGroupeTp("Groupe Tp");
        etudiant.setId("42");
        etudiant.setNom("Nom");
        etudiant.setNumEtudiant("Num Etudiant");
        etudiant.setPrenom("Prenom");
        //when
        when(this.etudiantRepository.save((Etudiant) any())).thenReturn(etudiant);
        when(this.etudiantRepository.findEtudiantByNumEtudiant((String) any())).thenReturn(Optional.empty());
        //assert
        assertThrows(EtudiantInnexistantException.class,
                () -> this.groupeTpService.addEtudiantInGroupe("Groupe", "Num Etudiant"));
        verify(this.groupeTpRepository).findByNumeroGroupe((String) any());
        verify(this.etudiantRepository).findEtudiantByNumEtudiant((String) any());
    }

    /**
     * Method under test: {@link GroupeTpService#deleteAllEtudiantInGroupeTp(String)}
     */
    @Test
    void testDeleteAllEtudiantInGroupeTp() {
        //when
        when(this.groupeTpRepository.save((GroupeTp) any())).thenReturn(new GroupeTp("Numero Groupe"));
        when(this.groupeTpRepository.findByNumeroGroupe((String) any()))
                .thenReturn(Optional.of(new GroupeTp("Numero Groupe")));
        this.groupeTpService.deleteAllEtudiantInGroupeTp("Numero Groupe");
        //assert
        verify(this.groupeTpRepository).save((GroupeTp) any());
        verify(this.groupeTpRepository).findByNumeroGroupe((String) any());
    }
}