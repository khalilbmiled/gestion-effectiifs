package com.bpi.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntrepriseRequestDto {
    private String nom;
    private String adresse;
}
