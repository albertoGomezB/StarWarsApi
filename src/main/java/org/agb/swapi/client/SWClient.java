package org.agb.swapi.client;

import org.agb.swapi.dto.FilmDTO;
import org.agb.swapi.dto.PersonDTO;
import org.agb.swapi.dto.PlanetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "star-wars", url = "https://swapi.dev/api/")
public interface SWClient {

    @GetMapping("/people/{id}")
    PersonDTO getPersonById(@RequestParam(value = "id") String id);

    @GetMapping("/films/{id}")
    FilmDTO getFilmById(@RequestParam(value = "id") String id);

    @GetMapping("/planets/{id}/")
    PlanetDTO getPlanetById(@PathVariable("id") String id);

    @GetMapping("/people/")
    PersonDTO searchPersonByName(@RequestParam("search") String name);

}
