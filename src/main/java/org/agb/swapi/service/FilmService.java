package org.agb.swapi.service;

import org.agb.swapi.dto.FilmDTO;

import java.util.List;

public interface FilmService {

    List<FilmDTO> getUpdatedFilms(List<FilmDTO> filmsDTO);
}
