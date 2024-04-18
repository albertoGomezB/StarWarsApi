package org.agb.swapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.agb.swapi.client.SWClient;
import org.agb.swapi.dto.FilmDTO;
import org.agb.swapi.dto.PersonDTO;
import org.agb.swapi.dto.PlanetDTO;
import org.agb.swapi.dto.SwapiResponseDTO;
import org.agb.swapi.exception.FilmNotFoundException;
import org.agb.swapi.exception.PlanetNotFoundException;
import org.agb.swapi.service.PersonService;
import org.agb.swapi.utility.ExtractUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the implementation of the PersonService interface.
 */

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final SWClient swClient;

    @Override
    public SwapiResponseDTO searchPersonByName(String name) {

        // Get the SwapiResponseDTO by name from the client
        SwapiResponseDTO swapiResponseDTO = swClient.searchPeopleByName(name);

        // Iterate through the results and update planet and films information
        List<PersonDTO> updatedResults = new ArrayList<>();
        for (PersonDTO personDTO : swapiResponseDTO.getResults()) {

            // Get the planet details using the extracted planet ID from the person's planet URL
            String planetId = personDTO.getHomeworld();

            // Extract the id from the planet url
            if(planetId  != null && !planetId.isEmpty()) {
                planetId = ExtractUrl.extractIdFromUrl(planetId);

            } else {
                throw new PlanetNotFoundException("Planet not found because planetId is null or empty");
            }

            PlanetDTO planetDTO = swClient.getPlanetById(planetId);
            String planetName = null;

            // Check if planetDTO is not null and contains a name
            if (planetDTO != null) {
                planetName = planetDTO.getName();
            } else {
                throw new PlanetNotFoundException("Planet not found because planetDTO is null");
            }

            // Set the planet name in the person DTO
            personDTO.setPlanet_name(planetName);

            // Get the films
            List<FilmDTO> filmsDTO = personDTO.getFilms();
            List<FilmDTO> updatedFilmsDTO = new ArrayList<>();

            // Add the updated films to the updatedFilmsDTO
            for (FilmDTO filmDTO : filmsDTO) {

                // Extract the id from the film url
                String filmId = ExtractUrl.extractIdFromUrl(filmDTO.getUrl());

                // Get the film details using the extracted film ID
                FilmDTO updatedFilmDTO = swClient.getFilmById(filmId);

                // Check if updatedFilmDTO is not null
                if (updatedFilmDTO != null && updatedFilmDTO.getTitle() != null && updatedFilmDTO.getRelease_date() != null) {
                    updatedFilmDTO.setName(updatedFilmDTO.getTitle()); // Set the name to the title for the response
                    updatedFilmsDTO.add(updatedFilmDTO);
                } else {
                    throw new FilmNotFoundException("Film not found because updatedFilmDTO is null or title/release_date is null");
                }
            }

            // Set the updated films to the personDTO
            personDTO.setFilms(updatedFilmsDTO);
            updatedResults.add(personDTO);
        }

        swapiResponseDTO.setResults(updatedResults);

        logger.info("SwapiResponseDTO after settings: {}", swapiResponseDTO);

        return swapiResponseDTO;
    }
}
