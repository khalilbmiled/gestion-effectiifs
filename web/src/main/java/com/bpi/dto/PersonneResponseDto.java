package com.bpi.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonneResponseDto {
    private UUID id;
    private String nom;
    private String prenom;
}
