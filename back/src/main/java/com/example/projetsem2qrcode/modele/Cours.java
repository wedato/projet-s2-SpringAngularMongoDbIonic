package com.example.projetsem2qrcode.modele;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Set;

@Document
@Data
public class Cours {
    @Id
    private String id;

    private String nom;
    private Prof prof;
    private LocalDate heureDebut;
    private LocalDate heureFin;
    private Set<GroupeTp> lesGroupes;

    public Cours(String nom, Prof prof, LocalDate heureDebut, LocalDate heureFin, Set<GroupeTp> lesGroupes) {
        this.nom = nom;
        this.prof = prof;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.lesGroupes = lesGroupes;
    }
}
