package com.bpi.services;

import com.bpi.models.PersonnePhysique;
import com.bpi.models.PersonnePhysiqueRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class EffectifServiceImpl implements IEffectifService{

    private final List<PersonnePhysique> listePersonnes = new ArrayList<>();

    @Override
    public PersonnePhysique savePeronnePhysique(PersonnePhysiqueRequest personnePhysiqueRequest) {
        PersonnePhysique personnePhysique = new PersonnePhysique(UUID.randomUUID(), personnePhysiqueRequest.getNom(), personnePhysiqueRequest.getPrenom(), "PP");
        listePersonnes.add(personnePhysique);
        return personnePhysique;
    }

}
