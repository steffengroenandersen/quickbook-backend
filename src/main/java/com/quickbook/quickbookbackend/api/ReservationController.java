package com.quickbook.quickbookbackend.api;

import com.quickbook.quickbookbackend.dto.HotelResponse;
import com.quickbook.quickbookbackend.dto.ReservationResponse;
import com.quickbook.quickbookbackend.service.ReservationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping // ADMIN
    public List<ReservationResponse> getAllReservations(){
        return reservationService.getAllReservations();
    }
    
    // getAllReservationsForHotelRoom
    @GetMapping("/room/{roomId}") // ANONYMOUS
    public List<ReservationResponse> getAllReservationsForHotelRoom(@PathVariable Integer roomId){
        return reservationService.getAllReservationsForHotelRoom(roomId);
    }
    // getAllReservationsForGuest
    @GetMapping("/guest/{guestUsername}") // ANONYMOUS
    public List<ReservationResponse> getAllReservationsForGuest(@PathVariable String guestUsername){
        return reservationService.getAllReservationsForGuest(guestUsername);
    }
}
