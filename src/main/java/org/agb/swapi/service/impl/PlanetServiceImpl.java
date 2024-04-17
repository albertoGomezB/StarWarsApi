package org.agb.swapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.agb.swapi.client.SWClient;
import org.agb.swapi.dto.PlanetDTO;
import org.agb.swapi.service.PlanetService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanetServiceImpl implements PlanetService {

    private final SWClient swClient;

    @Override
    public String getPlanetNameById(String planetId) {

        PlanetDTO planetDTO = swClient.getPlanetById(planetId);
        return planetDTO.getName();
    }
}
