package eci.arcn.project.booking.booking_event.dto;

import java.time.Instant;
import lombok.Data;

/**
 * Data Transfer Object (DTO) representing the information required
 * to create a new booking request.
 */
@Data
public class CreateBookingRequest {

    /**
     * The unique identifier of the room being booked.
     */
    private String roomId;

    /**
     * The start date of the booking.
     */
    private Instant startDate;

    /**
     * The finish date of the booking.
     */
    private Instant finishDate;

    /**
     * The total amount to be charged for the booking.
     */
    private double amount;

    /**
     * The client information associated with the booking.
     */
    private ClientDto client;
}
