package org.agb.swapi.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Star Wars API", description = "Endpoints to interact with the Star Wars API")
public class SWController {

    private final PersonService personService;

    @Operation(summary = "Search a person by name", description = "Search by name a person with the specified information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person found"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    @Parameter(name = "name", description = "Try with the name of the person to search", required = true)
    @GetMapping("/person-info")
    public ResponseEntity<PersonDTO> searchPersonByName(@RequestParam("name") String name) {

        SwapiResponseDTO responseDTO = personService.searchPersonByName(name); // Get the person information from the swapi

        if (responseDTO != null && !responseDTO.getResults().isEmpty()) {
            return ResponseEntity.ok(responseDTO.getResults().get(0)); // Return the first person found
        } else {
            throw new PersonNotFoundException("Person not found with the given name");
        }
    }

}