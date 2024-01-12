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
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDate;

import java.util.List;

@Service
public class ReservationService {
    ReservationRepository reservationRepository;
    HotelService hotelService;
    GuestRepository guestRepository;
    
    RoomRepository roomRepository;

    public ReservationService(ReservationRepository reservationRepository, HotelService hotelService, GuestRepository guestRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.hotelService = hotelService;
        this.guestRepository = guestRepository;
        this.roomRepository = roomRepository;
    }

    public List<ReservationResponse> getAllReservations(){
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream().map((reservation -> new ReservationResponse(reservation))).toList();
    }
    
    public List<ReservationResponse> getAllReservationsForHotelRoom(Integer roomId){
        List<Reservation> reservations = reservationRepository.findByRoomId(roomId);
        return reservations.stream().map((reservation -> new ReservationResponse(reservation))).toList();
    }

    public List<ReservationResponse> getAllReservationsForGuest(String guestUsername){
        List<Reservation> reservations = reservationRepository.findByGuestUsername(guestUsername);
        return reservations.stream().map((reservation -> new ReservationResponse(reservation))).toList();
    }
    
    public ReservationResponse deleteReservation(Integer reservationId, Principal principal){
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));
        
        if (!reservation.getGuest().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to delete this reservation");        }

        reservationRepository.delete(reservation);
        return new ReservationResponse(reservation);
    }
    
    public ReservationResponse makeReservation(Integer roomId, LocalDate reservationDate, Principal principal){
        
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found"));

        Guest guest = guestRepository.findByUsername(principal.getName());
        if (guest == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Guest not found");
        }
        
        if (!isRoomAvailable(room, reservationDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room is not available for the selected date");
        }
        
         

        Reservation reservation = new Reservation(guest, room, reservationDate);
        
         
        reservationRepository.save(reservation);
        return new ReservationResponse(reservation);


    }

    private boolean isRoomAvailable(Room room, LocalDate reservationDate) {
        List<Reservation> reservations = reservationRepository.findByRoomAndReservationDate(room, reservationDate);
        System.out.println(reservations.isEmpty());
        return reservations.isEmpty();
    }
}
