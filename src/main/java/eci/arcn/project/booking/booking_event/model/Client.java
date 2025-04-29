package eci.arcn.project.booking.booking_event.model;

import lombok.Data;

/**
 * Represents client information for a hotel booking.
 * 
 * Contains personal and contact information required for the booking process.
 */
@Data
public class Client {

    /**
     * Unique identifier for the user/client.
     */
    private String userId;

    /**
     * Full name of the client.
     */
    private String name;

    /**
     * Email address of the client.
     */
    private String userEmail;

    /**
     * Personal identification number of the client.
     */
    private int userPersonalId;

    /**
     * Cellphone number of the client.
     */
    private int cellphone;
}
