package org.agb.swapi.rest;

import lombok.RequiredArgsConstructor;
import org.agb.swapi.client.SWClient;
import org.agb.swapi.dto.FilmDTO;
import org.agb.swapi.dto.PersonDTO;
import org.agb.swapi.dto.PlanetDTO;
import org.agb.swapi.exception.PersonNotFoundException;
import org.agb.swapi.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/swapi-proxy")
@RequiredArgsConstructor
public class SWController {

    private final PersonService personService;
    private final SWClient swClient;

    @GetMapping("/person/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDTO findPersonById(@PathVariable String id) {
        return swClient.getPersonById(id);
    }

    @GetMapping("/movie/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FilmDTO findMovieById(@PathVariable String id) {
        return swClient.getFilmById(id);
    }

    @GetMapping("/planet/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlanetDTO findPlanetById(@PathVariable String id) {
        return swClient.getPlanetById(id);
    }

    @GetMapping("/person-info")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PersonDTO> searchPersonByName(@RequestParam("name") String name) {

        try {
            // Get the person by name from the SWService and return it
            PersonDTO personDTO = personService.searchPersonByName(name);
            return ResponseEntity.ok(personDTO);
        } catch (PersonNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
