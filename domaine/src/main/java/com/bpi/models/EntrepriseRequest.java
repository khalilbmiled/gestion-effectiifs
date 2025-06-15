package com.bpi.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntrepriseRequest {
    private String nom;
    private String adresse;
}
