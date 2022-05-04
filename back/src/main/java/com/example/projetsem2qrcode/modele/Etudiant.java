package com.example.projetsem2qrcode.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "etudiants")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Etudiant {
    @Id
    @JsonIgnore
    private String id;
    private String nom;
    private String prenom;
    private String numEtudiant;
    private String groupeTp;
    private boolean emargement;


    public Etudiant(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
}
