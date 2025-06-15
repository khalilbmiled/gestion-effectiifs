package com.bpi.mapper;


import com.bpi.dto.EntrepriseRequestDto;
import com.bpi.dto.EntrepriseResponseDto;
import com.bpi.models.Entreprise;
import com.bpi.models.EntrepriseRequest;

public class EntrepriseMapper {

    public static EntrepriseRequest mapToEntrepriseRequest(EntrepriseRequestDto entrepriseRequestDto){
        return EntrepriseRequest.builder()
                .nom(entrepriseRequestDto.getNom())
                .adresse(entrepriseRequestDto.getAdresse())
                .build();
    }

    public static EntrepriseResponseDto mapToEntrepriseResponseDto(Entreprise entreprise){

        return EntrepriseResponseDto.builder()
                .id(entreprise.getId())
                .nom(entreprise.getNom())
                .adresse(entreprise.getAdresse())
                .build();
    }

}
