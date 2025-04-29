package eci.arcn.project.booking.booking_event.facade;

import eci.arcn.project.booking.booking_event.dto.BookingResponse;
import eci.arcn.project.booking.booking_event.dto.ClientDto;
import eci.arcn.project.booking.booking_event.dto.CreateBookingRequest;
import eci.arcn.project.booking.booking_event.model.Booking;
import eci.arcn.project.booking.booking_event.model.Client;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class to map between Booking entities and DTOs.
 */
public class BookingMapper {

    private BookingMapper() {
        // Private constructor to prevent instantiation
    }

    /**
     * Maps a CreateBookingRequest to a Booking domain entity.
     *
     * @param request the incoming booking creation request
     * @return a Booking entity
     */
    public static Booking toBooking(CreateBookingRequest request) {
        Booking booking = new Booking();
        booking.setRoomId(request.getRoomId());
        booking.setStartDate(request.getStartDate());
        booking.setFinishDate(request.getFinishDate());
        booking.setAmount(request.getAmount());
        booking.setClient(toClient(request.getClient()));
        return booking;
    }

    /**
     * Maps a Booking domain entity to a BookingResponse DTO.
     *
     * @param booking the booking entity
     * @return the corresponding BookingResponse DTO
     */
    public static BookingResponse toBookingResponse(Booking booking) {
        BookingResponse response = new BookingResponse();
        response.setBookingId(booking.getBookingId());
        response.setCreatedDate(booking.getCreatedDate());
        response.setStartDate(booking.getStartDate());
        response.setFinishDate(booking.getFinishDate());
        response.setRoomId(booking.getRoomId());
        response.setClient(toClientDto(booking.getClient()));
        response.setBookingState(booking.getBookingState());
        response.setAmount(booking.getAmount());
        response.setRefundAmount(booking.getRefundAmount());
        return response;
    }

    /**
     * Maps a list of Booking entities to a list of BookingResponse DTOs.
     *
     * @param bookings the list of booking entities
     * @return a list of BookingResponse DTOs
     */
    public static List<BookingResponse> toBookingResponseList(List<Booking> bookings) {
        return bookings.stream()
                       .map(BookingMapper::toBookingResponse)
                       .collect(Collectors.toList());
    }

    /**
     * Maps a ClientDto to a Client domain entity.
     *
     * @param clientDto the client DTO
     * @return a Client domain entity
     */
    private static Client toClient(ClientDto clientDto) {
        if (clientDto == null) return null;

        Client client = new Client();
        client.setUserId(clientDto.getUserId());
        client.setName(clientDto.getName());
        client.setUserEmail(clientDto.getUserEmail());
        client.setUserPersonalId(clientDto.getUserPersonalId());
        client.setCellphone(clientDto.getCellphone());
        return client;
    }

    /**
     * Maps a Client domain entity to a ClientDto.
     *
     * @param client the client entity
     * @return the corresponding ClientDto
     */
    private static ClientDto toClientDto(Client client) {
        if (client == null) return null;

        ClientDto clientDto = new ClientDto();
        clientDto.setUserId(client.getUserId());
        clientDto.setName(client.getName());
        clientDto.setUserEmail(client.getUserEmail());
        clientDto.setUserPersonalId(client.getUserPersonalId());
        clientDto.setCellphone(client.getCellphone());
        return clientDto;
    }
}
