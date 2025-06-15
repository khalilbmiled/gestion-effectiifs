package com.bpi.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiareResponseDto {
    private UUID id;
    private String nom;
    private String prenom;
    private String adresse;
    private List<BeneficiareResponseDto> beneficiares;
}
