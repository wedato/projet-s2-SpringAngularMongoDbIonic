package com.example.projetsem2qrcode.service;

import com.example.projetsem2qrcode.repository.EtudiantRepository;
import com.example.projetsem2qrcode.repository.GroupeTpRepository;
import com.example.projetsem2qrcode.exceptions.*;
import com.example.projetsem2qrcode.modele.Etudiant;
import com.example.projetsem2qrcode.modele.GroupeTp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GroupeTpService {
    @Autowired
    private final GroupeTpRepository groupeTpRepository;

    @Autowired
    private final EtudiantRepository etudiantRepository;

    public GroupeTp saveGroupeTp (GroupeTp groupe) throws GroupeDejaCreerException, NomGroupeNonValideException {
        Optional<GroupeTp> groupeTp = groupeTpRepository.findByNumeroGroupe(groupe.getNumeroGroupe());
        if (groupeTp.isPresent()){
            throw new GroupeDejaCreerException();
        }
        if (groupe.getNumeroGroupe() == null || groupe.getNumeroGroupe().isBlank()){
            throw new NomGroupeNonValideException();
        }
        return groupeTpRepository.save(groupe);
    }

    public GroupeTp findGroupeByNumGroupe (String numeroGroupe) throws GroupeInnexistantException {
        Optional<GroupeTp> groupeTp = groupeTpRepository.findByNumeroGroupe(numeroGroupe);
        if (groupeTp.isPresent()){
            return groupeTp.get();
        }
        throw new GroupeInnexistantException();
    }

    public List<GroupeTp> getAllGroupeTp(){
        return groupeTpRepository.findAll();
    }

    public void deleteAllGroupeTp(){
        groupeTpRepository.deleteAll();
    }

    public void deleteGroupeByNumGroupe (String numeroGroupe) throws GroupeInnexistantException{
        Optional<GroupeTp> groupeTp = groupeTpRepository.findByNumeroGroupe(numeroGroupe);
        if (groupeTp.isPresent()){
            GroupeTp groupeSupp = groupeTp.get();
            groupeTpRepository.deleteById(groupeSupp.getId());
        }
        throw new GroupeInnexistantException();
    }

    public GroupeTp addEtudiantInGroupe(String groupe, String numEtudiant) throws GroupeInnexistantException, EtudiantInnexistantException, EtudiantDejaDansUnGroupeException {
        Optional<GroupeTp> groupeTp = groupeTpRepository.findByNumeroGroupe(groupe);
        Optional<Etudiant> etudiant = etudiantRepository.findEtudiantByNumEtudiant(numEtudiant);
        if (groupeTp.isEmpty()){
            throw new GroupeInnexistantException();
        }
        if (etudiant.isEmpty()){
            throw new EtudiantInnexistantException();
        }
        Etudiant _etudiant = etudiant.get();
        GroupeTp _groupeTp = groupeTp.get();

        if (_groupeTp.getListeEtudiantGroupe().contains(_etudiant)){
            throw new EtudiantDejaDansUnGroupeException();
        }
        _etudiant.setGroupeTp(_groupeTp.getNumeroGroupe());
        etudiantRepository.save(_etudiant);
        _groupeTp.getListeEtudiantGroupe().add(_etudiant);
        return groupeTpRepository.save(_groupeTp);
    }

    public void deleteAllEtudiantInGroupeTp(String numeroGroupe){
        Optional<GroupeTp> groupeTp = groupeTpRepository.findByNumeroGroupe(numeroGroupe);
        groupeTp.get().getListeEtudiantGroupe().clear();
        groupeTpRepository.save(groupeTp.get());
    }
}
