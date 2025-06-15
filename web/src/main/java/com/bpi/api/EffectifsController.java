package com.bpi.api;

import com.bpi.dto.EntrepriseRequestDto;
import com.bpi.dto.EntrepriseResponseDto;
import com.bpi.dto.PersonneRequestDto;
import com.bpi.dto.PersonneResponseDto;
import com.bpi.enums.TypeBeneficiaireInput;
import com.bpi.enums.TypeBeneficiare;
import com.bpi.exception.FunctionalException;
import com.bpi.mapper.BeneficiaireMapper;
import com.bpi.mapper.EntrepriseMapper;
import com.bpi.mapper.PersonnePhysiqueMapper;
import com.bpi.models.Beneficiaire;
import com.bpi.models.Entreprise;
import com.bpi.models.PersonnePhysique;
import com.bpi.services.IEffectifService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/effectifs")
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

    @Operation(summary = "Récupérer la liste des entreprises")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful get entreprises", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}),
            @ApiResponse(responseCode = "204" , description = "No Content: list of entreprises is empty", content = {@Content(mediaType = "application/json")})
    })
    @GetMapping(value = "/entreprise", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EntrepriseResponseDto>> getEntreprise() {
        List<Entreprise> entreprises = effectifService.getEntreprises();
        if(entreprises.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(entreprises.stream().map(EntrepriseMapper::mapToEntrepriseResponseDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @Operation(summary = "Ajouter un bénéficiaire entreprise / personne physique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful add beneficiaire", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}),
            @ApiResponse(responseCode = "204", description = "Entreprise ou personne inexistante",
                    content = @Content(mediaType = "String", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "404", description = "Return Code 404 - Not found",
                    content = @Content(mediaType = "String", schema = @Schema(type = "string"))),
    })
    @PostMapping(value = "/addBeneficiaire", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addBeneficiaire(
            @RequestHeader(value = "IDEntreprise")
            @Schema(name = "IDEntreprise" , description = "L'id de l'entreprise pour laquelle on va rajouter une personne physique")
            @Parameter(description = "l'identifiant de l'entreprise", required = true) final UUID idEntreprise,

            @RequestHeader(value = "IDBeneficiaire")
            @Schema(name = "IDBeneficiaire" , description = "L'id de la personne / enetreprise à rajouter dans une entreprise")
            @Parameter(description = "l'identifiant de la personne physique / entreprise", required = true) final UUID idPersonnePhysique,

            @RequestHeader(value = "type")
            @Schema(name = "type" , description = "Le type de beneficiaire entreprise/personne physique")
            @Parameter(description = "le type de beneficiaire (PP : Personne physique, EN : Entreprise)", required = true) final TypeBeneficiaireInput type
    ) {
        try {
            effectifService.addBeneficiaire(idEntreprise, idPersonnePhysique, String.valueOf(type));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (FunctionalException e) {

            HttpStatus status = HttpStatus.resolve(e.getErrorCode());
            if (status == null) {
                status = HttpStatus.BAD_REQUEST;
            }

            return ResponseEntity.status(status).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
        }
    }

    @Operation(summary = "Récupérer la liste des bénéficiaires")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful get beneficiaire", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))}),
            @ApiResponse(responseCode = "204", description = "La liste des bénéficiaires est vide",
                    content = @Content(mediaType = "String", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "404", description = "Return Code 404 - Not found",
                    content = @Content(mediaType = "String", schema = @Schema(type = "string"))),
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBeneficiaireEffectifs(
            @RequestHeader(value = "IDEntreprise")
            @Schema(name = "IDEntreprise" , description = "L'id de l'entreprise pour laquelle on va rajouter une personne physique")
            @Parameter(description = "l'identifiant de l'entreprise", required = true) final UUID idEntreprise,

            @RequestHeader(value = "type")
            @Schema(name = "type" , description = "Le type de beneficiaire entreprise/personne physique")
            @Parameter(description = "le type de beneficiaire (PP : Personne physique, EN : Entreprise, ALL : Entreprise + personne physique)", required = true) final TypeBeneficiare type
    ) {
        try {
            List<Beneficiaire> beneficiaires = effectifService.getBeneficiaireEffectifs(idEntreprise, String.valueOf(type));
            if(beneficiaires.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(beneficiaires.stream().map(BeneficiaireMapper::mapToBeneficiareResponseDto).collect(Collectors.toList()), HttpStatus.OK);
        } catch (FunctionalException e) {
            HttpStatus status = HttpStatus.resolve(e.getErrorCode());
            if (status == null) {
                status = HttpStatus.BAD_REQUEST;
            }
            return ResponseEntity.status(status).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
        }
    }
}
