package com.bpi.services;

import com.bpi.exception.FunctionalException;
import com.bpi.models.Entreprise;
import com.bpi.models.EntrepriseRequest;
import com.bpi.models.PersonnePhysique;
import com.bpi.models.PersonnePhysiqueRequest;
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

}
