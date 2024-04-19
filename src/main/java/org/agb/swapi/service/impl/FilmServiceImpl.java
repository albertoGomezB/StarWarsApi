package org.agb.swapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.agb.swapi.client.SWClient;
import org.agb.swapi.dto.FilmDTO;
import org.agb.swapi.exception.FilmNotFoundException;
import org.agb.swapi.service.FilmService;
import org.agb.swapi.utility.ExtractUrl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the implementation of the FilmService interface.
 */

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final SWClient swClient;

    @Override
    public List<FilmDTO> getUpdatedFilms(List<FilmDTO> filmsDTO) {

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
        return updatedFilmsDTO;
    }
}

