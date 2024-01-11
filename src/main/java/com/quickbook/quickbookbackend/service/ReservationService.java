package com.quickbook.quickbookbackend.service;

import com.quickbook.quickbookbackend.dto.HotelResponse;
import com.quickbook.quickbookbackend.dto.ReservationResponse;
import com.quickbook.quickbookbackend.entity.Hotel;
import com.quickbook.quickbookbackend.entity.Reservation;
import com.quickbook.quickbookbackend.entity.Room;
import com.quickbook.quickbookbackend.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    ReservationRepository reservationRepository;
    HotelService hotelService;

    public ReservationService(ReservationRepository reservationRepository, HotelService hotelService) {
        this.reservationRepository = reservationRepository;
        this.hotelService = hotelService;
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
}
