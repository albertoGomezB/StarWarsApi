package org.agb.swapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.agb.swapi.client.SWClient;
import org.agb.swapi.dto.PlanetDTO;
import org.agb.swapi.exception.PlanetNotFoundException;
import org.agb.swapi.service.PlanetService;
import org.agb.swapi.utility.ExtractUrl;
import org.springframework.stereotype.Service;

/**
 * This class represents the implementation of the PlanetService interface.
 */

@Service
@RequiredArgsConstructor
public class PlanetServiceImpl implements PlanetService {

    private final SWClient swClient;

    @Override
    public String getPlanetName(String planetUrl) {

        String planetId = ExtractUrl.extractLastOneCharacterFromUrl(planetUrl); // Extract the planet id
        PlanetDTO planetDTO = swClient.getPlanetById(planetId); // Get the planet information

        if(planetDTO != null) {
            return planetDTO.getName();
        } else {
            throw new PlanetNotFoundException("Planet not found because planetDTO is null");
        }
    }
}
