package com.bpi.services;

import com.bpi.exception.FunctionalException;
import com.bpi.models.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public interface IEffectifService {

    PersonnePhysique savePeronnePhysique(PersonnePhysiqueRequest personnePhysiqueRequest);
    List<PersonnePhysique> getPeronnePhysiques();

    Entreprise saveEntreprise(EntrepriseRequest entrepriseRequest);
    List<Entreprise> getEntreprises();

    void addBeneficiaire(UUID idEntreprise, UUID idBeneficiaire, String type) throws FunctionalException;
    List<Beneficiaire> getBeneficiaireEffectifs(UUID idEntreprise, String type) throws FunctionalException;

}
