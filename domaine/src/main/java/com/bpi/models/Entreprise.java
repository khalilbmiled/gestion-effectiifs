package com.bpi.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entreprise extends Beneficiaire{
    private String nom;
    private String adresse;
    private List<Beneficiaire> beneficiaires = new ArrayList<>();

    public Entreprise(UUID id, String nom, String adresse, String type) {
        super(id, type);
        this.nom = nom;
        this.adresse = adresse;
    }

    public void addBeneficiaire(Beneficiaire beneficiaire){
        beneficiaires.add(beneficiaire);
    }

}
