package com.bpi.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonnePhysiqueRequest {
    private String nom;
    private String prenom;
}
