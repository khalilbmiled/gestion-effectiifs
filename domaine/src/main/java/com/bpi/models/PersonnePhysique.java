package com.bpi.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonnePhysique extends Beneficiaire{
    private String nom;
    private String prenom;

    public PersonnePhysique(UUID id, String nom, String prenom, String type) {
        super(id, type);
        this.nom = nom;
        this.prenom = prenom;
    }
}
