package com.bpi.services;

import com.bpi.exception.FunctionalErrorCode;
import com.bpi.exception.FunctionalException;
import com.bpi.models.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class EffectifServiceImpl implements IEffectifService{

    private final List<PersonnePhysique> listePersonnes = new ArrayList<>();
    private final List<Entreprise> listeEntreprises = new ArrayList<>();

    private static final String ENTREPRISE_INEXISTANTE = "ENTREPRISE INEXISTENTE";
    private static final String PERSONNE_INEXISTANTE = "PERSONNE INEXISTENTE";
    private static final String TYPE_INEXISTANT = "TYPE INEXISTENT";

    private static final String PERSONNE_PHYSIQUE = "PP";
    private static final String ENTREPRISE = "EN";



    @Override
    public PersonnePhysique savePeronnePhysique(PersonnePhysiqueRequest personnePhysiqueRequest) {
        PersonnePhysique personnePhysique = new PersonnePhysique(UUID.randomUUID(), personnePhysiqueRequest.getNom(), personnePhysiqueRequest.getPrenom(), "PP");
        listePersonnes.add(personnePhysique);
        return personnePhysique;
    }

    @Override
    public List<PersonnePhysique> getPeronnePhysiques() {
        return listePersonnes;
    }

    @Override
    public Entreprise saveEntreprise(EntrepriseRequest entrepriseRequest) {
        Entreprise entreprise = new Entreprise(UUID.randomUUID(), entrepriseRequest.getNom(), entrepriseRequest.getAdresse(), "EN");
        listeEntreprises.add(entreprise);
        return entreprise;
    }

    @Override
    public List<Entreprise> getEntreprises() {
        return listeEntreprises;
    }

    @Override
    public void addBeneficiaire(UUID idEntreprise, UUID idBeneficiaire, String type) throws FunctionalException {
        Optional<Entreprise> entrepriseOptional = listeEntreprises.stream().filter(entreprise -> entreprise.getId().equals(idEntreprise)).findFirst();

        if(entrepriseOptional.isEmpty()){
            throw new FunctionalException(FunctionalErrorCode.BAD_REQUEST, ENTREPRISE_INEXISTANTE);
        }

        Entreprise entreprise = entrepriseOptional.get();

        if(type.equals(PERSONNE_PHYSIQUE)) {
            Optional<PersonnePhysique> personnePhysique = listePersonnes.stream().filter(personne -> personne.getId().equals(idBeneficiaire)).findFirst();
            if(personnePhysique.isEmpty()){
                throw new FunctionalException(FunctionalErrorCode.BAD_REQUEST, PERSONNE_INEXISTANTE);
            }
            PersonnePhysique personne =  personnePhysique.get();
            entreprise.addBeneficiaire(personne);
        } else if (type.equals(ENTREPRISE)) {
            Optional<Entreprise> subEntrepriseOptional = listeEntreprises.stream().filter(e -> e.getId().equals(idBeneficiaire)).findFirst();
            if(subEntrepriseOptional.isEmpty()){
                throw new FunctionalException(FunctionalErrorCode.BAD_REQUEST, ENTREPRISE_INEXISTANTE);
            }
            Entreprise subEntreprise = subEntrepriseOptional.get();
            entreprise.addBeneficiaire(subEntreprise);
        } else {
            throw new FunctionalException(FunctionalErrorCode.BAD_REQUEST, TYPE_INEXISTANT);
        }
    }

    @Override
    public List<Beneficiaire> getBeneficiaireEffectifs(UUID idEntreprise, String type) throws FunctionalException {
        Optional<Entreprise> entrepriseOptional = listeEntreprises.stream().filter(entreprise -> entreprise.getId().equals(idEntreprise)).findFirst();

        if(entrepriseOptional.isEmpty()){
            throw new FunctionalException(FunctionalErrorCode.BAD_REQUEST, ENTREPRISE_INEXISTANTE);
        }

        Entreprise entreprise = entrepriseOptional.get();
        if(type.equals("ALL")) {
            return entreprise.getBeneficiaires();
        } else {
            return entreprise.getBeneficiaires().stream().filter(beneficiaire -> beneficiaire.getType().equals(type)).toList();
        }
    }

}
