package org.agb.swapi.service;

import org.agb.swapi.dto.FilmDTO;

public interface FilmService {

    FilmDTO getFilmById(String id);
}
