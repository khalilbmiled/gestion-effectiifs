package com.bpi.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonneRequestDto {
    private String nom;
    private String prenom;
}
