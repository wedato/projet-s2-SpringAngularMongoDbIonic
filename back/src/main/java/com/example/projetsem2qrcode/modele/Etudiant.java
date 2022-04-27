package com.example.projetsem2qrcode.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "etudiants")
@AllArgsConstructor
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

}
