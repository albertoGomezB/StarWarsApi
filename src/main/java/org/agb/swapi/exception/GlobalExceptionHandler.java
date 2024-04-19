package org.agb.swapi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

/**
 * This class manage the exceptions to the application in a global way.
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handlePersonNotFoundException(PersonNotFoundException e) {
        String errorMessage = "Person with the given name not found";
        return handleNotFoundException(errorMessage);
    }

    @ExceptionHandler(PlanetNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handlePlanetNotFoundException(PlanetNotFoundException e) {
        String errorMessage = "Planet not found";
        return handleNotFoundException(errorMessage);
    }

    @ExceptionHandler(FilmNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleFilmNotFoundException(FilmNotFoundException e) {
        String errorMessage = "Film not found";
        return handleNotFoundException(errorMessage);
    }

    @ExceptionHandler(StarshipNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleStarshipNotFoundException(StarshipNotFoundException e) {
        String errorMessage = "Starship not found";
        return handleNotFoundException(errorMessage);
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleVehicleNotFoundException(VehicleNotFoundException e) {
        String errorMessage = "Vehicle not found";
        return handleNotFoundException(errorMessage);
    }

    private ResponseEntity<ApiError> handleNotFoundException(String errorMessage) {

        LocalDateTime timestamp = LocalDateTime.now();
        String path = getRequestPath();

        ApiError errorResponse = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .error("Resource not found")
                .message(errorMessage)
                .timestamp(timestamp)
                .path(path)
                .build();

        log.error("Resource not found: {}", errorMessage);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * This method retrieves the request path from the current request.
     */
    private String getRequestPath() {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return attributes.getRequest().getRequestURI();
        }
        return null;
    }
}