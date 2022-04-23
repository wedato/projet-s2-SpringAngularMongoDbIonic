package com.example.projetsem2qrcode.service;

import com.example.projetsem2qrcode.dao.EtudiantRepository;
import com.example.projetsem2qrcode.modele.Etudiant;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return etudiantRepository.save(etudiant);
    }

    public Etudiant findByNumEtudiant(String numEtudiant) {
        Optional<Etudiant> etudiant = etudiantRepository.findEtudiantByNumEtudiant(numEtudiant);
        return etudiant.isPresent() ? etudiant.get() : null;
    }

    public void deleteAllEtudiant() {
        etudiantRepository.deleteAll();
    }

    public void deleteEtudiantByNumEtudiant(String numEtudiant){
        Optional<Etudiant> etudiants = etudiantRepository.findEtudiantByNumEtudiant(numEtudiant);
        if (etudiants.isPresent()){
            String id = etudiants.get().getId();
            etudiantRepository.deleteById(id);
        }
    }

    public Etudiant updateEtudiantByNumEtudiant(String numEtudiant , Etudiant etudiantUp){
        Optional<Etudiant> etudiant = etudiantRepository.findEtudiantByNumEtudiant(numEtudiant);
        if (etudiant.isPresent()){
            Etudiant etucurrent = etudiant.get();
            etucurrent.setNom(etudiantUp.getNom());
            etucurrent.setPrenom(etudiantUp.getPrenom());
            etucurrent.setNumEtudiant(etudiantUp.getNumEtudiant());
            etucurrent.setGroupeTp(etudiantUp.getGroupeTp());
            return etudiantRepository.save(etucurrent);
        }
        return null;
    }

    public List<Etudiant> getAllEtudiant() {
        List<Etudiant> etudiants = new ArrayList<Etudiant>();
        etudiantRepository.findAll().forEach(etudiants::add);
        return etudiants;
    }
}
