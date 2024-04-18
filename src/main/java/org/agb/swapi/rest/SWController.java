package org.agb.swapi.rest;

import lombok.RequiredArgsConstructor;
import org.agb.swapi.dto.PersonDTO;
import org.agb.swapi.dto.SwapiResponseDTO;
import org.agb.swapi.exception.PersonNotFoundException;
import org.agb.swapi.service.PersonService;
import org.springframework.http.HttpStatus;
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
        try {
            // Get the person information from the SWAPI
            SwapiResponseDTO responseDTO = personService.searchPersonByName(name);

            if (responseDTO != null && !responseDTO.getResults().isEmpty()) {
                // Return the first person found
                return ResponseEntity.ok(responseDTO.getResults().getFirst());

            } else {
                throw new PersonNotFoundException("Person not found with the given name");
            }
        } catch (PersonNotFoundException e) {
            throw e;
        }
    }

}