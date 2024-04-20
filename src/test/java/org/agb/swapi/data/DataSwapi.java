package org.agb.swapi.data;

import org.agb.swapi.dto.FilmDTO;
import org.agb.swapi.dto.PersonDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that provides data for testing
 */
public class DataSwapi {

    /**
     * Returns a PersonDTO object with the data of Luke Skywalker
     * @return
     */
    public static PersonDTO getPersonData() {

        PersonDTO personDTO = new PersonDTO();
        personDTO.setName("Luke Skywalker");
        personDTO.setBirth_year("19BBY");
        personDTO.setGender("male");
        personDTO.setPlanet_name("Tatooine");
        personDTO.setFastest_vehicle_driven("X-wing");

        // List of films in which Luke Skywalker appears
        List<FilmDTO> films = new ArrayList<>();
        films.add(new FilmDTO("A New Hope", "1977-05-25"));
        films.add(new FilmDTO("The Empire Strikes Back", "1980-05-17"));
        films.add(new FilmDTO("Return of the Jedi", "1983-05-25"));
        films.add(new FilmDTO("Revenge of the Sith", "2005-05-19"));
        personDTO.setFilms(films);

        return personDTO;
    }

}

