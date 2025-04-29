package eci.arcn.project.booking.booking_event.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import eci.arcn.project.booking.booking_event.repository.BookingRepository;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest{

    @Test
    void createBookingWithoutUserId_throwsException(){

    }

    @Test
    void createBookingWithoutUserName_throwsException(){

    }

    @Test
    void createBookingWithoutUserEmail_throwsException(){

    }

    @Test
    void createBookingWithoutUserPersonalId_throwsException(){

    }

    @Test
    void createBookingWithoutUserCellphone_throwsException(){

    }

    @Test
    void createBookingWithUser_returnsBookingId(){

    }

    @Test
    void createBookingWithInvalidStartDate_throwsException(){

    }


    @Test
    void createBookingWithInvalidFinishDate_throwsException(){

    }

    @Test
    void cancelBookingWithoutBookingId_throwsException(){

    }

    @Test
    void cancelBookingWithinThreeDayLeftsToStartDate_refundIsCutTo100percent(){

    }

    @Test
    void cancelBookingWithOneWeekBeforeStartDate_refundIsCutTo20percentOfInitialPayment(){

    }

    @Test
    void rejectBookingWithoutBookingId_throwsException(){

    }

    @Test //room is booked by another
    void createBookingWhenRoomIsUnavailable_throwsException(){

    }


    @Test
    void cancelBookingWhenRoomIsUnavailable_updateBookingState(){

    }





    
}