package org.agb.swapi.service;

import org.agb.swapi.dto.PersonDTO;

public interface PersonService {

    PersonDTO searchPersonByName(String name);
}
