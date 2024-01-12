package com.quickbook.quickbookbackend.service;

import com.quickbook.quickbookbackend.dto.ReservationResponse;
import com.quickbook.quickbookbackend.entity.Guest;
import com.quickbook.quickbookbackend.entity.Reservation;
import com.quickbook.quickbookbackend.entity.Room;
import com.quickbook.quickbookbackend.repository.GuestRepository;
import com.quickbook.quickbookbackend.repository.ReservationRepository;
import com.quickbook.quickbookbackend.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    ReservationService reservationService;
    @Mock
    ReservationRepository reservationRepository;
    @Mock
    HotelService hotelService;
    @Mock
    GuestRepository guestRepository;
    @Mock
    RoomRepository roomRepository;
  
    @BeforeEach
    void setUp(){
        reservationService = new ReservationService(reservationRepository, hotelService,guestRepository,roomRepository);
    }
    
    Guest mockGuest(){
        Guest mockGuest = new Guest("mockUsername", "mockEmail", "mockPass", "mockFirstName", "mockLastName");
        guestRepository.save(mockGuest);
        return mockGuest;
    }
    
    Room mockRoom(){
        Room mockRoom = new Room(1,2);
        roomRepository.save(mockRoom);
        return mockRoom;
    }
    
    Reservation mockReservation(Guest mockGuest, Room mockRoom){
        LocalDate reservationDate = LocalDate.now();
        Reservation testReservation = new Reservation(mockGuest, mockRoom, reservationDate);
        return testReservation;
    }
    
    @Test
    void getAllReservations() {
        // Arrange
        Reservation testReservation = mockReservation(mockGuest(), mockRoom());
        when(reservationRepository.findAll()).thenReturn(List.of(testReservation));
        
        // Act
        List<ReservationResponse> reservationResponses = reservationService.getAllReservations();
        
        // Assert
        assertEquals(1, reservationResponses.size());
    }

    @Test
    void getAllReservationsForHotelRoom() {
        // Arrange
        Reservation testReservation = mockReservation(mockGuest(), mockRoom());
        when(reservationRepository.findByRoomId(1)).thenReturn(List.of(testReservation));
        
        // Act
        List<ReservationResponse> reservationResponses = reservationService.getAllReservationsForHotelRoom(1);
        
        // Assert
        assertEquals(1, reservationResponses.size());
    }

    @Test
    void getAllReservationsForGuest() {
        // Arrange
        Guest mockGuest = mockGuest();
        Reservation testReservation = mockReservation(mockGuest(), mockRoom());
        when(reservationRepository.findByGuestUsername(mockGuest.getUsername())).thenReturn(List.of(testReservation));
        
        // Act
        List<ReservationResponse> reservationResponses = reservationService.getAllReservationsForGuest(mockGuest.getUsername());
        
        // Assert
        assertEquals(1, reservationResponses.size());

    }

    @Test
    void deleteReservation() {
        // Arrange
        Guest mockGuest = mockGuest();
        Reservation testReservation = mockReservation(mockGuest, mockRoom());
        Principal principal = () -> mockGuest.getUsername();
        when(reservationRepository.findById(testReservation.getId())).thenReturn(java.util.Optional.of(testReservation));

        // Act
        ReservationResponse deletedReservation = reservationService.deleteReservation(testReservation.getId(), principal);

        // Assert
        verify(reservationRepository, times(1)).delete(testReservation);
    }
    
    @Test
    void deleteNonExistentReservation() {
        // Arrange
        Guest mockGuest = mockGuest();
        int nonExistentReservationId = 999;
        Principal principal = () -> mockGuest.getUsername();
        when(reservationRepository.findById(nonExistentReservationId)).thenReturn(java.util.Optional.empty());

        // Act and Assert
        assertThrows(ResponseStatusException.class, () -> {
            reservationService.deleteReservation(nonExistentReservationId, principal);
        }, "Should throw ResponseStatusException with HttpStatus.NOT_FOUND");
    }
}
