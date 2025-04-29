package eci.arcn.project.booking.booking_event.dto;

import java.time.Instant;

import eci.arcn.project.booking.booking_event.model.BookingState;
import lombok.Data;

/**
 * DTO representing the response details of a booking.
 * 
 * Used to expose booking information to API consumers.
 */
@Data
public class BookingResponse {

    /**
     * Unique identifier for the booking.
     */
    private String bookingId;

    /**
     * Date and time when the booking was created.
     */
    private Instant createdDate;

    /**
     * Date and time when the booking starts.
     */
    private Instant startDate;

    /**
     * Date and time when the booking ends.
     */
    private Instant finishDate;

    /**
     * Identifier for the room associated with the booking.
     */
    private String roomId;

    /**
     * Client information associated with the booking.
     */
    private ClientDto client;

    /**
     * Current state of the booking (e.g., CONFIRMED, CANCELLED).
     */
    private BookingState bookingState;

    /**
     * Total amount for the booking.
     */
    private double amount;

    /**
     * Refund amount calculated in case of cancellation (nullable).
     */
    private Double refundAmount;
}
