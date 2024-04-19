package org.agb.swapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.agb.swapi.client.SWClient;
import org.agb.swapi.dto.*;
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

        SwapiResponseDTO swapiResponseDTO = swClient.searchPeopleByName(name); // Get the people information
        List<PersonDTO> updatedResults = new ArrayList<>();

        for (PersonDTO personDTO : swapiResponseDTO.getResults()) {

            String planetId = personDTO.getHomeworld(); // Get the planet URL

            if(planetId  != null && !planetId.isEmpty()) {
                planetId = ExtractUrl.extractLastOneCharacterFromUrl(planetId); // Extract the planet id
            } else {
                throw new PlanetNotFoundException("Planet not found because planetId is null or empty");
            }

            PlanetDTO planetDTO = swClient.getPlanetById(planetId); // Get the planet information
            String planetName = null;

            if (planetDTO != null) {
                planetName = planetDTO.getName();
            } else {
                throw new PlanetNotFoundException("Planet not found because planetDTO is null");
            }

            personDTO.setPlanet_name(planetName);

            List<FilmDTO> filmsDTO = personDTO.getFilms();
            List<FilmDTO> updatedFilmsDTO = new ArrayList<>();

            for (FilmDTO filmDTO : filmsDTO) {

                String filmId = ExtractUrl.extractLastOneCharacterFromUrl(filmDTO.getUrl()); // Extract the film id
                FilmDTO updatedFilmDTO = swClient.getFilmById(filmId); // Get the film information

                if (updatedFilmDTO != null && updatedFilmDTO.getTitle() != null && updatedFilmDTO.getRelease_date() != null) {
                    updatedFilmDTO.setName(updatedFilmDTO.getTitle()); // Set the film name to the title
                    updatedFilmsDTO.add(updatedFilmDTO);
                } else {
                    throw new FilmNotFoundException("Film not found because updatedFilmDTO is null or title/release_date is null");
                }
            }
            personDTO.setFilms(updatedFilmsDTO); // Set the updated films

            List<VehicleDTO> vehiclesDTO = personDTO.getVehicles();
            List<StarshipDTO> starshipsDTO = personDTO.getStarships();

            String fastestVehicleDriven = null;
            String maxSpeed = "0";
            String currentSpeed;

            for (VehicleDTO vehicleDTO : vehiclesDTO) {
                String vehicleId = ExtractUrl.extractLastTwoCharactersFromUrl(vehicleDTO.getUrl()); // Extract the vehicle id
                VehicleDTO updatedVehicleDTO = swClient.getVehicleById(vehicleId); // Get the vehicle information

                if (updatedVehicleDTO != null && updatedVehicleDTO.getMax_atmosphering_speed() != null) {

                    currentSpeed = updatedVehicleDTO.getMax_atmosphering_speed();
                    if (Integer.parseInt(currentSpeed) > Integer.parseInt(maxSpeed)) {
                        maxSpeed = currentSpeed; // Set the max speed to the vehicle speed if it is greater than the current max speed
                        fastestVehicleDriven = updatedVehicleDTO.getName(); // Set the fastest vehicle driven to the vehicle name
                    }
                }
            }

            for (StarshipDTO starshipDTO : starshipsDTO) {

                String starshipId = ExtractUrl.extractLastTwoCharactersFromUrl(starshipDTO.getUrl()); // Extract the starship id
                StarshipDTO updatedStarshipDTO = swClient.getStarshipById(starshipId); // Get the starship information

                if (updatedStarshipDTO != null && updatedStarshipDTO.getMax_atmosphering_speed() != null
                        && (Integer.parseInt(updatedStarshipDTO.getMax_atmosphering_speed()) > Integer.parseInt(maxSpeed))) {

                    maxSpeed = updatedStarshipDTO.getMax_atmosphering_speed(); // Set the max speed to the starship speed if it is greater than the current max speed
                    fastestVehicleDriven = updatedStarshipDTO.getName(); // Set the fastest vehicle driven to the starship name

                }
            }
            personDTO.setFastest_vehicle_driven(fastestVehicleDriven); // Set the fastest vehicle driven to the personDTO

            updatedResults.add(personDTO); // Add the updated personDTO to the updated results
        }

        swapiResponseDTO.setResults(updatedResults); // Set the updated results to the swapiResponseDTO

        return swapiResponseDTO;
    }
}
