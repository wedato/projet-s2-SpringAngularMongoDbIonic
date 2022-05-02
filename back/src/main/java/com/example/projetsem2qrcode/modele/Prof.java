package com.example.projetsem2qrcode.modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Data
@AllArgsConstructor
public class Prof {

    @Id
    private String id;
    private String nom;
    private String prenom;
    private Set<Cours> coursDuProf;


}
