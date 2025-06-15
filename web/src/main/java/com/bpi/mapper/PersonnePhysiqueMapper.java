package com.bpi.mapper;

import com.bpi.models.PersonnePhysique;
import com.bpi.dto.PersonneRequestDto;
import com.bpi.dto.PersonneResponseDto;
import com.bpi.models.PersonnePhysiqueRequest;

public class PersonnePhysiqueMapper {

    public static PersonnePhysiqueRequest mapToPersonnePhysiqueRequest(final PersonneRequestDto personneRequestDto) {
        return PersonnePhysiqueRequest.builder()
                .nom(personneRequestDto.getNom())
                .prenom(personneRequestDto.getPrenom())
                .build();
    }

    public static PersonneResponseDto mapToPersonneResponseDto(final PersonnePhysique personnePhysique) {
        return PersonneResponseDto.builder()
                .id(personnePhysique.getId())
                .nom(personnePhysique.getNom())
                .prenom(personnePhysique.getPrenom())
                .build();
    }

}
