package eci.arcn.project.booking.booking_event.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the Booking Event API.
 * 
 * Catches and translates exceptions into appropriate HTTP responses.
 * Provides centralized error handling across all controllers.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles IllegalArgumentException thrown by the application.
     *
     * @param ex the thrown IllegalArgumentException
     * @return a HTTP 400 Bad Request response with the exception message
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    /**
     * Handles IllegalStateException thrown by the application.
     *
     * @param ex the thrown IllegalStateException
     * @return a HTTP 400 Bad Request response with the exception message
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalState(IllegalStateException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    /**
     * Handles any unexpected exceptions not explicitly handled elsewhere.
     *
     * @param ex the thrown Exception
     * @return a HTTP 500 Internal Server Error response with a generic error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.internalServerError().body("Internal Server Error: " + ex.getMessage());
    }
}
