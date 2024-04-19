package org.agb.swapi.rest;

import lombok.RequiredArgsConstructor;
import org.agb.swapi.dto.PersonDTO;
import org.agb.swapi.dto.SwapiResponseDTO;
import org.agb.swapi.exception.PersonNotFoundException;
import org.agb.swapi.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class represents the REST controller that will handle the requests to the SWAPI proxy.
 */
@RestController
@RequestMapping("/swapi-proxy")
@RequiredArgsConstructor
public class SWController {

    private final PersonService personService;

    @GetMapping("/person-info")
    public ResponseEntity<PersonDTO> searchPersonByName(@RequestParam("name") String name) {

        SwapiResponseDTO responseDTO = personService.searchPersonByName(name); // Get the person information from the swapi

        if (responseDTO != null && !responseDTO.getResults().isEmpty()) {
            return ResponseEntity.ok(responseDTO.getResults().getFirst()); // Return the first person found
        } else {
            throw new PersonNotFoundException("Person not found with the given name");
        }
    }

}