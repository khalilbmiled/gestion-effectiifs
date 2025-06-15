package com.bpi.dto;

import lombok.*;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntrepriseResponseDto {
    private UUID id;
    private String nom;
    private String adresse;

}
