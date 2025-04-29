package eci.arcn.project.booking.booking_event.service;

import java.util.List;
import eci.arcn.project.booking.booking_event.model.Booking;

/**
 * Service interface for booking operations.
 * 
 * Defines the contract for creating, managing, and retrieving bookings.
 */
public interface BookingService {

    /**
     * Creates a new booking.
     *
     * @param booking the booking details to be created
     * @return the unique ID of the created booking
     */
    String createBooking(Booking booking);

    /**
     * Cancels an existing booking.
     *
     * @param bookingId the ID of the booking to cancel
     */
    void cancelBooking(String bookingId);

    /**
     * Rejects a booking request.
     *
     * @param bookingId the ID of the booking to reject
     */
    void rejectBooking(String bookingId);

    /**
     * Retrieves a booking by its unique ID.
     *
     * @param bookingId the ID of the booking
     * @return the Booking details
     */
    Booking getBookingById(String bookingId);

    /**
     * Retrieves all bookings associated with a given user ID.
     *
     * @param userId the user ID to filter bookings
     * @return a list of bookings belonging to the user
     */
    List<Booking> getBookingsByUserId(String userId);

    /**
     * Retrieves all bookings associated with a given user email.
     *
     * @param userEmail the user email to filter bookings
     * @return a list of bookings belonging to the user
     */
    List<Booking> getBookingsByUserEmail(String userEmail);
}
