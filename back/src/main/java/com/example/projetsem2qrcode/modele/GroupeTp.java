package com.example.projetsem2qrcode.modele;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class GroupeTp {

    @Id
    private String id;
    private String numeroGroupe;
    private Set<Etudiant> listeEtudiantGroupe;

    public GroupeTp(String numeroGroupe) {
        this.numeroGroupe = numeroGroupe;
        this.listeEtudiantGroupe = new HashSet<>();
    }
}
