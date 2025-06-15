package com.bpi.api;

import com.bpi.dto.EntrepriseRequestDto;
import com.bpi.dto.EntrepriseResponseDto;
import com.bpi.dto.PersonneRequestDto;
import com.bpi.dto.PersonneResponseDto;
import com.bpi.mapper.EntrepriseMapper;
import com.bpi.mapper.PersonnePhysiqueMapper;
import com.bpi.models.Entreprise;
import com.bpi.models.PersonnePhysique;
import com.bpi.services.IEffectifService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Créer une nouvelle personne physique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful save personne", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PersonneResponseDto.class))})
    })
    @PostMapping(value = "/personne", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonneResponseDto> addPersonnePhysique(
            @RequestBody final PersonneRequestDto personneRequestDto
    ) {
        PersonnePhysique personnePhysique = effectifService.savePeronnePhysique(PersonnePhysiqueMapper.mapToPersonnePhysiqueRequest(personneRequestDto));
        return new ResponseEntity<>(PersonnePhysiqueMapper.mapToPersonneResponseDto(personnePhysique), HttpStatus.CREATED);
    }

    @Operation(summary = "Récupérer la liste des personnes physiques")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful get personnes physiques", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "No Content: list of personne physique is empty", content = {@Content(mediaType = "application/json")})})
    @GetMapping(value = "/personne", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonneResponseDto>> getPersonne() {
        List<PersonnePhysique> listePersonnes = effectifService.getPeronnePhysiques();
        if(listePersonnes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listePersonnes.stream().map(PersonnePhysiqueMapper::mapToPersonneResponseDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @Operation(summary = "Créer une nouvelle entreprise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful save entreprise", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EntrepriseResponseDto.class))})
    })
    @PostMapping(value = "/entreprise", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EntrepriseResponseDto> addEntreprise(
            @RequestBody final EntrepriseRequestDto entrepriseRequestDto
    ) {
        Entreprise entreprise = effectifService.saveEntreprise(EntrepriseMapper.mapToEntrepriseRequest(entrepriseRequestDto));
        return new ResponseEntity<>(EntrepriseMapper.mapToEntrepriseResponseDto(entreprise), HttpStatus.CREATED);
    }
}
