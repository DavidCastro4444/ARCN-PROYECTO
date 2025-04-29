package eci.arcn.project.booking.booking_event.service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import eci.arcn.project.booking.booking_event.model.Booking;
import eci.arcn.project.booking.booking_event.model.BookingState;
import eci.arcn.project.booking.booking_event.model.Client;
import eci.arcn.project.booking.booking_event.repository.BookingRepository;
import lombok.RequiredArgsConstructor;

/**
 * Implementation of the BookingService interface.
 * 
 * Handles business logic for creating, cancelling, rejecting, and retrieving bookings.
 */
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public String createBooking(Booking booking) {
        validateClient(booking.getClient());
        validateDates(booking.getStartDate(), booking.getFinishDate());

        // Check room availability
        if (!isRoomAvailable(booking.getRoomId(), booking.getStartDate(), booking.getFinishDate())) {
            booking.setBookingState(BookingState.UNAVAILABLE_ROOM);
            throw new IllegalStateException("Room is unavailable");
        }

        booking.setBookingId(UUID.randomUUID().toString());
        booking.setCreatedDate(Instant.now());
        booking.setBookingState(BookingState.PENDING);

        bookingRepository.save(booking);

        return booking.getBookingId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelBooking(String bookingId) {
        if (isNullOrEmpty(bookingId)) {
            throw new IllegalArgumentException("Booking ID is required to cancel");
        }

        Booking booking = bookingRepository.findById(bookingId);
        if (booking == null) {
            throw new IllegalStateException("Booking not found");
        }

        Instant now = Instant.now();
        long daysToStart = Duration.between(now, booking.getStartDate()).toDays();

        if (daysToStart <= 3) {
            booking.setRefundAmount(booking.getAmount() * 1.0); // 100% refund
        } else if (daysToStart <= 7) {
            booking.setRefundAmount(booking.getAmount() * 0.2); // 20% refund
        } else {
            booking.setRefundAmount(booking.getAmount()); // Full refund
        }

        booking.setBookingState(BookingState.CANCELLED);
        bookingRepository.save(booking);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rejectBooking(String bookingId) {
        if (isNullOrEmpty(bookingId)) {
            throw new IllegalArgumentException("Booking ID is required to reject");
        }

        Booking booking = bookingRepository.findById(bookingId);
        if (booking == null) {
            throw new IllegalStateException("Booking not found");
        }

        booking.setBookingState(BookingState.REJECTED);
        bookingRepository.save(booking);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Booking getBookingById(String bookingId) {
        if (isNullOrEmpty(bookingId)) {
            throw new IllegalArgumentException("Booking ID is required");
        }

        Booking booking = bookingRepository.findById(bookingId);
        if (booking == null) {
            throw new IllegalStateException("Booking not found");
        }

        return booking;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Booking> getBookingsByUserId(String userId) {
        if (isNullOrEmpty(userId)) {
            throw new IllegalArgumentException("User ID is required");
        }

        return bookingRepository.findByUserId(userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Booking> getBookingsByUserEmail(String userEmail) {
        if (isNullOrEmpty(userEmail)) {
            throw new IllegalArgumentException("User email is required");
        }

        return bookingRepository.findByUserEmail(userEmail);
    }

    // ====== Private helper methods ======

    /**
     * Validates the client information.
     *
     * @param client the client to validate
     * @throws IllegalArgumentException if any required client field is missing or invalid
     */
    private void validateClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client information is required");
        }

        if (isNullOrEmpty(client.getUserId())) {
            throw new IllegalArgumentException("Client userId is required");
        }
        if (isNullOrEmpty(client.getName())) {
            throw new IllegalArgumentException("Client name is required");
        }
        if (isNullOrEmpty(client.getUserEmail())) {
            throw new IllegalArgumentException("Client email is required");
        }
        if (client.getUserPersonalId() <= 0) {
            throw new IllegalArgumentException("Client personal ID must be positive");
        }
        if (client.getCellphone() <= 0) {
            throw new IllegalArgumentException("Client cellphone must be positive");
        }
    }

    /**
     * Validates that the start and finish dates are correctly set.
     *
     * @param startDate the start date
     * @param finishDate the finish date
     * @throws IllegalArgumentException if dates are missing or invalid
     */
    private void validateDates(Instant startDate, Instant finishDate) {
        if (startDate == null || finishDate == null) {
            throw new IllegalArgumentException("Start and finish dates are required");
        }
        if (startDate.isAfter(finishDate)) {
            throw new IllegalArgumentException("Start date must be before finish date");
        }
    }

    /**
     * Checks room availability for the given period.
     *
     * @param roomId the room identifier
     * @param startDate booking start date
     * @param finishDate booking finish date
     * @return true if the room is available, false otherwise
     */
    private boolean isRoomAvailable(String roomId, Instant startDate, Instant finishDate) {
        // TODO: Implement check against real room availability
        return true;
    }

    /**
     * Utility method to check if a String is null or empty.
     *
     * @param str the string to check
     * @return true if null or empty, false otherwise
     */
    private boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
