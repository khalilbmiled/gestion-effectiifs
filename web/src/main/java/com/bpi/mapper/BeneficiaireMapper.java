package com.bpi.mapper;

import com.bpi.dto.BeneficiareResponseDto;
import com.bpi.models.Beneficiaire;
import com.bpi.models.Entreprise;
import com.bpi.models.PersonnePhysique;


public class BeneficiaireMapper {

    public static BeneficiareResponseDto mapToBeneficiareResponseDto(Beneficiaire beneficiaire) {
        if(beneficiaire instanceof PersonnePhysique personnePhysique) {
            return BeneficiareResponseDto.builder()
                    .id(personnePhysique.getId())
                    .nom(personnePhysique.getNom())
                    .prenom(personnePhysique.getPrenom())
                    .build();
        } else {
            Entreprise entreprise = (Entreprise) beneficiaire;
            return BeneficiareResponseDto.builder()
                    .id(entreprise.getId())
                    .nom(entreprise.getNom())
                    .adresse(entreprise.getAdresse())
                    .build();
        }
    }
}
