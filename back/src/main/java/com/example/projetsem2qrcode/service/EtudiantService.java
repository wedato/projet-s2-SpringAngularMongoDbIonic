package com.example.projetsem2qrcode.service;

import com.example.projetsem2qrcode.exceptions.EtudiantInnexistantException;
import com.example.projetsem2qrcode.exceptions.NumEtudiantDejaPresentException;
import com.example.projetsem2qrcode.modele.Etudiant;
import com.example.projetsem2qrcode.repository.EtudiantRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EtudiantService {

    @Autowired
    private final EtudiantRepository etudiantRepository;

    public Etudiant saveEtudiant(Etudiant etudiant) throws NumEtudiantDejaPresentException {
        Optional<Etudiant> etudiantOptional = etudiantRepository.findEtudiantByNumEtudiant(etudiant.getNumEtudiant());
        if (etudiantOptional.isPresent()){
           throw new NumEtudiantDejaPresentException();
        }

        etudiant.setNumEtudiant(generateNumEtu());
        return etudiantRepository.save(etudiant);
    }

    public Etudiant findByNumEtudiant(String numEtudiant) throws EtudiantInnexistantException {
        Optional<Etudiant> etudiant = etudiantRepository.findEtudiantByNumEtudiant(numEtudiant);
        if (etudiant.isPresent()){
            return etudiant.get();
        }
        throw new EtudiantInnexistantException();
    }

    public void deleteAllEtudiant() {
        etudiantRepository.deleteAll();
    }

    public void deleteEtudiantByNumEtudiant(String numEtudiant) throws EtudiantInnexistantException {
        Optional<Etudiant> etudiants = etudiantRepository.findEtudiantByNumEtudiant(numEtudiant);
        if (etudiants.isPresent()){
            String id = etudiants.get().getId();
            etudiantRepository.deleteById(id);
        }
        throw new EtudiantInnexistantException();
    }

    public Etudiant updateEtudiantByNumEtudiant(String numEtudiant , Etudiant etudiantUp) throws EtudiantInnexistantException {
        Optional<Etudiant> etudiant = etudiantRepository.findEtudiantByNumEtudiant(numEtudiant);
        if (etudiant.isPresent()){
            Etudiant etucurrent = etudiant.get();
            etucurrent.setNom(etudiantUp.getNom());
            etucurrent.setPrenom(etudiantUp.getPrenom());
            etucurrent.setNumEtudiant(etudiantUp.getNumEtudiant());
            etucurrent.setGroupeTp(etudiantUp.getGroupeTp());
            return etudiantRepository.save(etucurrent);
        }
        throw new EtudiantInnexistantException();
    }

    public List<Etudiant> getAllEtudiant() {
        return etudiantRepository.findAll();
    }

    public void reinitEmargement(){
        List<Etudiant> etudiants = etudiantRepository.findAll();
        for (Etudiant etudiant : etudiants){
            etudiant.setEmargement(false);
        }
    }

    private String generateNumEtu() {
        return RandomStringUtils.randomNumeric(7);
    }
}
