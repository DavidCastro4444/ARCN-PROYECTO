package eci.arcn.project.booking.booking_event.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import eci.arcn.project.booking.booking_event.dto.BookingResponse;
import eci.arcn.project.booking.booking_event.dto.CreateBookingRequest;
import eci.arcn.project.booking.booking_event.facade.BookingMapper;
import eci.arcn.project.booking.booking_event.model.Booking;
import eci.arcn.project.booking.booking_event.service.BookingService;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Controller that exposes the REST API endpoints for Booking management.
 * 
 * Provides operations to create, cancel, reject, and retrieve bookings by different criteria.
 */
@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    /**
     * Creates a new booking.
     *
     * @param request the booking details including client information
     * @return the generated booking ID
     */
    @Operation(summary = "Create a new booking", description = "Creates a new booking with client and room details.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Booking created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid booking details"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<String> createBooking(@RequestBody CreateBookingRequest request) {
        Booking booking = BookingMapper.toBooking(request);
        String bookingId = bookingService.createBooking(booking);
        return ResponseEntity.ok(bookingId);
    }

    /**
     * Cancels an existing booking by its booking ID.
     *
     * @param bookingId the ID of the booking to cancel
     * @return a HTTP 200 OK response if successful
     */
    @Operation(summary = "Cancel a booking", description = "Cancels an existing booking using the booking ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Booking cancelled successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid booking ID provided"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{bookingId}/cancel")
    public ResponseEntity<Void> cancelBooking(@PathVariable String bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok().build();
    }

    /**
     * Rejects a booking by its booking ID.
     *
     * @param bookingId the ID of the booking to reject
     * @return a HTTP 200 OK response if successful
     */
    @Operation(summary = "Reject a booking", description = "Rejects a booking request using the booking ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Booking rejected successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid booking ID provided"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{bookingId}/reject")
    public ResponseEntity<Void> rejectBooking(@PathVariable String bookingId) {
        bookingService.rejectBooking(bookingId);
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves a booking by its booking ID.
     *
     * @param bookingId the ID of the booking
     * @return the booking details wrapped in a BookingResponse object
     */
    @Operation(summary = "Retrieve a booking by ID", description = "Fetches a booking using its unique booking ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Booking retrieved successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid booking ID provided"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable String bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(BookingMapper.toBookingResponse(booking));
    }

    /**
     * Retrieves all bookings associated with a specific user ID.
     *
     * @param userId the user ID to search for
     * @return a list of bookings for the given user ID
     */
    @Operation(summary = "Retrieve bookings by user ID", description = "Fetches all bookings associated with a specific user ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bookings retrieved successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid user ID provided"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponse>> getBookingsByUserId(@PathVariable String userId) {
        List<Booking> bookings = bookingService.getBookingsByUserId(userId);
        return ResponseEntity.ok(BookingMapper.toBookingResponseList(bookings));
    }

    /**
     * Retrieves all bookings associated with a specific user email.
     *
     * @param userEmail the user's email to search for
     * @return a list of bookings for the given email
     */
    @Operation(summary = "Retrieve bookings by user email", description = "Fetches all bookings associated with a specific user email address.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bookings retrieved successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid user email provided"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/user/email/{userEmail}")
    public ResponseEntity<List<BookingResponse>> getBookingsByUserEmail(@PathVariable String userEmail) {
        List<Booking> bookings = bookingService.getBookingsByUserEmail(userEmail);
        return ResponseEntity.ok(BookingMapper.toBookingResponseList(bookings));
    }
}
