package eci.arcn.project.booking.booking_event.repository;

import java.util.List;
import eci.arcn.project.booking.booking_event.model.Booking;

/**
 * Repository interface for managing Booking persistence operations.
 * 
 * Defines basic operations for storing, retrieving, and querying bookings.
 */
public interface BookingRepository {

    /**
     * Saves a booking to the repository.
     *
     * @param booking the booking to be saved
     */
    void save(Booking booking);

    /**
     * Finds a booking by its unique identifier.
     *
     * @param bookingId the ID of the booking
     * @return the Booking if found, or null if not found
     */
    Booking findById(String bookingId);

    /**
     * Retrieves all bookings associated with a specific user ID.
     *
     * @param userId the user ID to search by
     * @return a list of bookings belonging to the user
     */
    List<Booking> findByUserId(String userId);

    /**
     * Retrieves all bookings associated with a specific user email.
     *
     * @param userEmail the user email to search by
     * @return a list of bookings belonging to the user
     */
    List<Booking> findByUserEmail(String userEmail);
}
