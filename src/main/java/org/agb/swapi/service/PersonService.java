package org.agb.swapi.service;

import org.agb.swapi.dto.SwapiResponseDTO;

public interface PersonService {

    SwapiResponseDTO searchPersonByName(String name);
}
