package com.bpi.services;

import com.bpi.models.Entreprise;
import com.bpi.models.EntrepriseRequest;
import com.bpi.models.PersonnePhysique;
import com.bpi.models.PersonnePhysiqueRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IEffectifService {

    PersonnePhysique savePeronnePhysique(PersonnePhysiqueRequest personnePhysiqueRequest);
    List<PersonnePhysique> getPeronnePhysiques();

    Entreprise saveEntreprise(EntrepriseRequest entrepriseRequest);
    List<Entreprise> getEntreprises();


}
