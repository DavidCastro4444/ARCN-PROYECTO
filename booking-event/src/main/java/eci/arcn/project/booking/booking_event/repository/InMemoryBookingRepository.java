package eci.arcn.project.booking.booking_event.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import eci.arcn.project.booking.booking_event.model.Booking;

/**
 * In-memory implementation of the BookingRepository.
 * 
 * Stores bookings using a thread-safe ConcurrentHashMap.
 * Intended for development, testing, or non-persistent environments.
 */
@Repository
public class InMemoryBookingRepository implements BookingRepository {

    private final Map<String, Booking> bookings = new ConcurrentHashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Booking booking) {
        bookings.put(booking.getBookingId(), booking);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Booking findById(String bookingId) {
        return bookings.get(bookingId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Booking> findByUserId(String userId) {
        List<Booking> result = new ArrayList<>();
        for (Booking booking : bookings.values()) {
            if (booking.getClient() != null && userId.equals(booking.getClient().getUserId())) {
                result.add(booking);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Booking> findByUserEmail(String userEmail) {
        List<Booking> result = new ArrayList<>();
        for (Booking booking : bookings.values()) {
            if (booking.getClient() != null && userEmail.equals(booking.getClient().getUserEmail())) {
                result.add(booking);
            }
        }
        return result;
    }
}
