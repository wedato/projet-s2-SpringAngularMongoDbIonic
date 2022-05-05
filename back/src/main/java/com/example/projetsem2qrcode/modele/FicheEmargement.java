package com.example.projetsem2qrcode.modele;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "fiche_emargements")
@Data
public class FicheEmargement {

    @Id
    public String id;
    public String nomCours;
    public List<User> listeEtudiantSigne;

    public FicheEmargement(String nomCours) {
        this.nomCours = nomCours;
    }
}
