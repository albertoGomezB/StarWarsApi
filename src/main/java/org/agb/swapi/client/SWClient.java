package org.agb.swapi.client;

import org.agb.swapi.dto.FilmDTO;
import org.agb.swapi.dto.PlanetDTO;
import org.agb.swapi.dto.SwapiResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "star-wars", url = "https://swapi.dev/api/")
public interface SWClient {

    @GetMapping("/films/{id}")
    FilmDTO getFilmById(@PathVariable(value = "id") String id);

    @GetMapping("/planets/{id}/")
    PlanetDTO getPlanetById(@PathVariable("id") String id);

    @GetMapping("/people/")
    SwapiResponseDTO searchPeopleByName(@RequestParam("search") String name);

}
