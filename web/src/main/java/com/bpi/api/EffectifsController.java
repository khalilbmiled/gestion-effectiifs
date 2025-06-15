package com.bpi.api;

import com.bpi.dto.PersonneRequestDto;
import com.bpi.dto.PersonneResponseDto;
import com.bpi.mapper.PersonnePhysiqueMapper;
import com.bpi.models.PersonnePhysique;
import com.bpi.services.IEffectifService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/effectif")
public class EffectifsController {

    private final IEffectifService effectifService;

    public EffectifsController(@Qualifier("effectifServiceImpl") IEffectifService effectifService){
        this.effectifService = effectifService;
    }

    @PostMapping(value = "/personne", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonneResponseDto> addPersonnePhysique(
            @RequestBody final PersonneRequestDto personneRequestDto
    ) {
        PersonnePhysique personnePhysique = effectifService.savePeronnePhysique(PersonnePhysiqueMapper.mapToPersonnePhysiqueRequest(personneRequestDto));
        return new ResponseEntity<>(PersonnePhysiqueMapper.mapToPersonneResponseDto(personnePhysique), HttpStatus.CREATED);
    }

    @GetMapping(value = "/personne", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonneResponseDto>> getPersonne() {
        List<PersonnePhysique> listePersonnes = effectifService.getPeronnePhysiques();
        if(listePersonnes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listePersonnes.stream().map(PersonnePhysiqueMapper::mapToPersonneResponseDto).collect(Collectors.toList()), HttpStatus.OK);
    }
}
