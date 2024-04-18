package org.agb.swapi.exception;

public class PlanetNotFoundException extends RuntimeException {

    public PlanetNotFoundException(String message) {
        super(message);
    }
}
