package org.agb.swapi.exception;

public class VehicleNotFoundException extends RuntimeException {

        public VehicleNotFoundException(String message) {
            super(message);
        }
}
