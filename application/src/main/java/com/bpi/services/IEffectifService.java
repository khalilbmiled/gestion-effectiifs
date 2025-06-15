package com.bpi.services;

import com.bpi.models.PersonnePhysique;
import com.bpi.models.PersonnePhysiqueRequest;
import org.springframework.stereotype.Service;


@Service
public interface IEffectifService {

    PersonnePhysique savePeronnePhysique(PersonnePhysiqueRequest personnePhysiqueRequest);

}
