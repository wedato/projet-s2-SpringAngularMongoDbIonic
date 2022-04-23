package com.example.projetsem2qrcode.modele;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "etudiants")
@Getter
@Setter
public class Etudiant {
    @Id
    private String id;
    private String nom;
    private String prenom;
    @Indexed(unique = true)
    private String numEtudiant;
    private String groupeTp;

    public Etudiant() {
    }

    public Etudiant(String nom, String prenom, String numEtudiant) {
        this.nom = nom;
        this.prenom = prenom;
        this.numEtudiant = numEtudiant;
    }
}
