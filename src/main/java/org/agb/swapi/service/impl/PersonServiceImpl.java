package org.agb.swapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.agb.swapi.client.SWClient;
import org.agb.swapi.dto.FilmDTO;
import org.agb.swapi.dto.PersonDTO;
import org.agb.swapi.service.PersonService;
import org.agb.swapi.service.PlanetService;
import org.agb.swapi.utility.ExtractUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final SWClient swClient;
    private final PlanetService planetService;

    @Override
    public PersonDTO searchPersonByName(String name) {

        logger.info("PersonName : {}", name);

        // Get the person by name from the SWClient
        PersonDTO personDTO = swClient.searchPersonByName(name);
        logger.info("PersonDTO retrieved from SWClient: {}", personDTO);

        if (personDTO.getFilms() != null) {

            // Get the movies from the person
            List<FilmDTO> filmsDTO = personDTO.getFilms();
            List<FilmDTO> updatedFilmsDTO = new ArrayList<>();

            // Add the updated films to the updatedFilmsDTO
            for (FilmDTO filmDTO : filmsDTO) {

                // Extract the id from the film url
                FilmDTO updatedFilmDTO = swClient.getFilmById(ExtractUrl.extractIdFromUrl(filmDTO.getUrl()));
                updatedFilmsDTO.add(updatedFilmDTO);
            }

            // Set the updated films to the personDTO
            personDTO.setFilms(updatedFilmsDTO);
        }

        // Get the planet name by id from the Planet Service and set it to the personDTO
        String planetName = planetService.getPlanetNameById(personDTO.getPlanet_name());
        personDTO.setPlanet_name(planetName);
        logger.info("PersonDTO after setting planet name: {}", personDTO);

        return personDTO;
    }

}