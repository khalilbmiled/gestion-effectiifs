package com.bpi.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Beneficiaire {
    private UUID id;
    private String type;
}
