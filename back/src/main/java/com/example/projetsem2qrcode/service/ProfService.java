package com.example.projetsem2qrcode.service;

import com.example.projetsem2qrcode.dao.ProfRepository;
import com.example.projetsem2qrcode.exceptions.NomProfInvalideException;
import com.example.projetsem2qrcode.exceptions.PrenomProfInvalideException;
import com.example.projetsem2qrcode.exceptions.ProfDejaCreerException;
import com.example.projetsem2qrcode.exceptions.ProfInnexistantExcepton;
import com.example.projetsem2qrcode.modele.Prof;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProfService {

    @Autowired
    private ProfRepository profRepository;

    public Prof saveProf (Prof prof) throws NomProfInvalideException, PrenomProfInvalideException, ProfDejaCreerException {
        if (prof.getNom() == null || prof.getNom().isBlank()){
            throw new NomProfInvalideException();
        }
        if (prof.getPrenom() == null || prof.getPrenom().isBlank()){
            throw new PrenomProfInvalideException();
        }
        Optional<Prof> prof1 = profRepository.findByNom(prof.getNom());
        if (prof1.isPresent()){
            throw new ProfDejaCreerException();
        }
        Prof profSave = prof1.get();
        return profRepository.save(profSave);
    }

    public Prof findProfByNom(String nomProf) throws ProfInnexistantExcepton {
        Optional<Prof> prof = profRepository.findByNom(nomProf);
        if (prof.isPresent()) {
            return prof.get();
        }
        throw new ProfInnexistantExcepton();
    }

    public List<Prof> getAllProf(){
        return profRepository.findAll();
    }

    public void deleteProfByNom(String nomProf) throws ProfInnexistantExcepton{
        Optional<Prof> prof = profRepository.findByNom(nomProf);
        if (prof.isPresent()){
            Prof profSupp = prof.get();
            profRepository.deleteById(profSupp.getId());
        }
        throw new ProfInnexistantExcepton();
    }

    public void deleteAllProf(){
        profRepository.deleteAll();
    }

    public void deleteAllCoursForProf(String nomProf) throws ProfInnexistantExcepton{
        Optional<Prof> prof = profRepository.findByNom(nomProf);
        if (prof.isPresent()){
            prof.get().getCoursDuProf().clear();
        }
        throw new ProfInnexistantExcepton();
    }
}
