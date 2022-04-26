package com.example.projetsem2qrcode.service;

import com.example.projetsem2qrcode.repository.CoursRepository;
import com.example.projetsem2qrcode.repository.GroupeTpRepository;
import com.example.projetsem2qrcode.exceptions.CoursInnexistantException;
import com.example.projetsem2qrcode.exceptions.GroupeInnexistantException;
import com.example.projetsem2qrcode.exceptions.GroupeTpDejaAjouterException;
import com.example.projetsem2qrcode.modele.Cours;
import com.example.projetsem2qrcode.modele.GroupeTp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CoursService {

    @Autowired
    private CoursRepository coursRepository;

    @Autowired
    private GroupeTpRepository groupeTpRepository;

    public Cours createCours(Cours cours) {
        Optional<Cours> coursOptional = coursRepository.findById(cours.getId());
        if (coursOptional.isPresent()){
            return coursRepository.save(cours);
        }
        return null;
    }

    public Cours findByNomDuCours(String nomCours){
        Optional<Cours> coursOptional = coursRepository.findCoursByNom(nomCours);
        return coursOptional.isPresent() ? coursOptional.get() : null;
    }

    public void deleteAllCours(){
        coursRepository.deleteAll();
    }

    public void deleteCourByNom(String nomCours) {
        Optional<Cours> coursOptional = coursRepository.findCoursByNom(nomCours);
        if (coursOptional.isPresent()){
            String id = coursOptional.get().getId();
            coursRepository.deleteById(id);
        }
    }

    public List<Cours> getAllCours(){
        List<Cours> cours = new ArrayList<>();
        coursRepository.findAll().forEach(cours::add);
        return cours;
    }

    public Cours updateCours(String nomCours, Cours coursUp) throws CoursInnexistantException{
        Optional<Cours> optionalCours = coursRepository.findCoursByNom(nomCours);
        if (optionalCours.isPresent()){
            Cours coursCurrent = optionalCours.get();
            coursCurrent.setNom(coursUp.getNom());
            coursCurrent.setProf(coursUp.getProf());
            coursCurrent.setHeureDebut(coursUp.getHeureDebut());
            coursCurrent.setHeureFin(coursUp.getHeureFin());
            coursCurrent.setLesGroupes(coursUp.getLesGroupes());
            return coursRepository.save(coursCurrent);
        }
        throw new CoursInnexistantException();
    }

    public Cours addGroupeTPAuCours(String nomCours, String numeroGroupe) throws GroupeInnexistantException, CoursInnexistantException, GroupeTpDejaAjouterException {
        Optional<GroupeTp> groupeTp = groupeTpRepository.findByNumeroGroupe(numeroGroupe);
        Optional<Cours> cours = coursRepository.findCoursByNom(nomCours);
        if(cours.isEmpty()){
            throw new CoursInnexistantException();
        }
        if (groupeTp.isEmpty()){
            throw new GroupeInnexistantException();
        }
        GroupeTp _groupeTp = groupeTp.get();
        Cours _cours = cours.get();
        for (GroupeTp gr : _cours.getLesGroupes()){
            if (_groupeTp.getListeEtudiantGroupe().contains(gr)){
                throw new GroupeTpDejaAjouterException();
            }
        }
        _cours.getLesGroupes().add(_groupeTp);
        return coursRepository.save(_cours);
    }

    public Cours addProfAuCours(String nomCours, String nomProf){
        return null;
    }
}
