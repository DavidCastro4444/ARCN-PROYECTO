package eci.arcn.project.booking.booking_event.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) representing client information
 * used during booking creation or retrieval.
 */
@Data
public class ClientDto {

    /**
     * The unique user ID of the client.
     */
    private String userId;

    /**
     * The full name of the client.
     */
    private String name;

    /**
     * The email address of the client.
     */
    private String userEmail;

    /**
     * The personal identification number of the client.
     */
    private int userPersonalId;

    /**
     * The cellphone number of the client.
     */
    private int cellphone;
}
