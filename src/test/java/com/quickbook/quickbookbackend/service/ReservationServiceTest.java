package com.quickbook.quickbookbackend.service;

import com.quickbook.quickbookbackend.dto.HotelResponse;
import com.quickbook.quickbookbackend.dto.ReservationResponse;
import com.quickbook.quickbookbackend.entity.Guest;
import com.quickbook.quickbookbackend.entity.Hotel;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
    void setUp() {
        reservationService = new ReservationService(reservationRepository, hotelService, guestRepository, roomRepository);
    }
    
    Guest generateTestUser(){
        return new Guest("testUser1", "testUser@localhost.com", "pass", "Steffen", "Andersen");
    }
    
    Hotel generateTestHotel(){
        return new Hotel("Test Hotel", "123 Main St", "Test City", 12345, "Test Country");
    }
    
    Room generateTestRoom(){
        return new Room(1, 2);
    }
    
    Reservation generateReservation(Guest testGuest, Room testRoom, LocalDate reservationDate ){
        return new Reservation(testGuest, testRoom, reservationDate);
    }
    
    @Test
    void getAllReservations_ReturnsAllReservationsAsReservationResponses() {
        
        // Arrange
        Guest testGuest = generateTestUser();

        Hotel testHotel = generateTestHotel();
        Room testRoom = generateTestRoom();
        
        List<Room> testRoomList = new ArrayList<>();
        testRoomList.add(testRoom);
        testHotel.setRooms(testRoomList);

        LocalDate reservationDate = LocalDate.now();
        Reservation reservation1 = generateReservation(testGuest, testRoom, reservationDate);
        Reservation reservation2 = generateReservation(testGuest, testRoom, reservationDate);
        
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);
        reservations.add(reservation2);
        
        when(reservationRepository.findAll()).thenReturn(reservations);
        
        // Act
        List<ReservationResponse> testReservations = reservationService.getAllReservations();
        
        // Assert
        assertEquals(2, testReservations.size(), "The getAllReservations method should return 1 reservation");
        
        for(ReservationResponse reservationResponse : testReservations){
            assertTrue(reservationResponse instanceof ReservationResponse,"Each element should be an instance of ReservationResponse");
        }
    }

    @Test
    void getAllReservationsForHotelRoom_ReturnsAllReservationsAsReservationResponses() {
        // Arrange
        Guest testGuest = generateTestUser();

        Hotel testHotel = generateTestHotel();
        Room testRoom = generateTestRoom();

        List<Room> testRoomList = new ArrayList<>();
        testRoomList.add(testRoom);
        testHotel.setRooms(testRoomList);

        LocalDate reservationDate = LocalDate.now();
        Reservation reservation1 = generateReservation(testGuest, testRoom, reservationDate);
        Reservation reservation2 = generateReservation(testGuest, testRoom, reservationDate);

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);
        reservations.add(reservation2);
        
        when(reservationRepository.findByRoomId(1)).thenReturn(reservations);
        
        // Act
        List<ReservationResponse> reservationResponses = reservationService.getAllReservationsForHotelRoom(1);
        
        // Assert
        assertEquals(2, reservationResponses.size(), "The getAllReservationsForHotelRoom method should return 2 reservations");

        for(ReservationResponse reservationResponse : reservationResponses){
            assertTrue(reservationResponse instanceof ReservationResponse,"Each element should be an instance of ReservationResponse");
        }
    }

    @Test
    void getAllReservationsForGuest() {
    }

    @Test
    void deleteReservation() {
    }

    @Test
    void makeReservation() {
    }
}