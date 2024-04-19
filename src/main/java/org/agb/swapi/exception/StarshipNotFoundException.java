package org.agb.swapi.exception;

public class StarshipNotFoundException extends RuntimeException {

    public StarshipNotFoundException(String message) {
        super(message);
    }
}
