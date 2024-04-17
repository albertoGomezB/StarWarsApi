package org.agb.swapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.agb.swapi.client.SWClient;
import org.agb.swapi.dto.FilmDTO;
import org.agb.swapi.service.FilmService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final SWClient swClient;

    @Override
    public FilmDTO getFilmById(String id) {
        return swClient.getFilmById(id);
    }
}
