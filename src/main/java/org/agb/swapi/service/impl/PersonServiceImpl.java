package org.agb.swapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.agb.swapi.client.SWClient;
import org.agb.swapi.dto.*;
import org.agb.swapi.exception.PersonNotFoundException;
import org.agb.swapi.service.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the implementation of the PersonService interface.
 */

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final SWClient swClient;
    private final PlanetService planetService;
    private final FilmService filmService;
    private final VehicleService vehicleService;

    @Override
    @Cacheable(value = "people", key = "#name")
    public SwapiResponseDTO searchPersonByName(String name) {

        SwapiResponseDTO swapiResponseDTO = swClient.searchPeopleByName(name); // Get the people information

        if(swapiResponseDTO == null || swapiResponseDTO.getResults() == null || swapiResponseDTO.getResults().isEmpty()) {
            throw new PersonNotFoundException("Person not found with the given name");
        }
        List<PersonDTO> updatedResults = new ArrayList<>();

        for (PersonDTO personDTO : swapiResponseDTO.getResults()) {

            String planetId = personDTO.getHomeworld();
            String planetName = planetService.getPlanetName(planetId);
            personDTO.setPlanet_name(planetName);

            List<FilmDTO> updatedFilmsDTO = filmService.getUpdatedFilms(personDTO.getFilms());
            personDTO.setFilms(updatedFilmsDTO);

            String fastestVehicleOrStarshipDriven = vehicleService.getFastestVehicleOrStarshipDrivenName(personDTO.getVehicles(), personDTO.getStarships());
            personDTO.setFastest_vehicle_driven(fastestVehicleOrStarshipDriven);

            updatedResults.add(personDTO);
        }

        swapiResponseDTO.setResults(updatedResults);

        return swapiResponseDTO;
    }
}
