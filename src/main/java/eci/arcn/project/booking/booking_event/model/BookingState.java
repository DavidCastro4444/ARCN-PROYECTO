package eci.arcn.project.booking.booking_event.model;

/**
 * Enumeration representing the possible states of a booking.
 * 
 * A booking can transition through these states during its lifecycle.
 */
public enum BookingState {

    /**
     * The booking has been cancelled by the user or system.
     */
    CANCELLED,

    /**
     * The booking request has been rejected (e.g., invalid request, room not available).
     */
    REJECTED,

    /**
     * The booking has been confirmed and is active.
     */
    CONFIRMED,

    /**
     * The booking could not be created because the room was unavailable.
     */
    UNAVAILABLE_ROOM,

    /**
     * The booking has been created but is still pending confirmation.
     */
    PENDING
}
