package org.agb.swapi.exception;

public class FilmNotFoundException extends RuntimeException {

    public FilmNotFoundException(String message) {

        super(message);
    }
}
