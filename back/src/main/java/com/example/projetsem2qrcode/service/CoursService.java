package com.example.projetsem2qrcode.service;

import com.example.projetsem2qrcode.dao.CoursRepository;
import com.example.projetsem2qrcode.modele.Cours;
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

    public Cours updateCours(String nomCours, Cours coursUp){
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
        return null;
    }

    public Cours addGroupeTPAuCours(String nomCours, String nomGroupeTP){
        return null;
    }

    public Cours addProfAuCours(String nomCours, String nomProf){
        return null;
    }
}
