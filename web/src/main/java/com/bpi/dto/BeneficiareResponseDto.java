package com.bpi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeneficiareResponseDto {
    private UUID id;
    private String nom;
    private String prenom;
    private String adresse;
    private List<BeneficiareResponseDto> beneficiares;
}
