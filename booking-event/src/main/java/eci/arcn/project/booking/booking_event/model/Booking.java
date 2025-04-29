package eci.arcn.project.booking.booking_event.model;

import java.time.Instant;
import lombok.Data;

/**
 * Represents a hotel room booking made by a client.
 * 
 * Contains booking details such as room information, dates, payment amount,
 * and the client who made the booking.
 */
@Data
public class Booking {

    /**
     * Unique identifier for the booking.
     */
    private String bookingId;

    /**
     * The date when the booking was created.
     */
    private Instant createdDate;

    /**
     * The start date of the booked stay.
     */
    private Instant startDate;

    /**
     * The end date of the booked stay.
     */
    private Instant finishDate;

    /**
     * The ID of the room associated with this booking.
     */
    private String roomId;

    /**
     * The client who made the booking.
     */
    private Client client;

    /**
     * The current state of the booking (e.g., confirmed, cancelled).
     */
    private BookingState bookingState;

    /**
     * The total amount charged for the booking.
     */
    private double amount;

    /**
     * The amount to refund to the client in case of cancellation (can be null if no
     * refund applicable).
     */
    private Double refundAmount = null;
}
