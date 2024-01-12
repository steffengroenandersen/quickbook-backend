package com.quickbook.quickbookbackend.api;

import com.quickbook.quickbookbackend.dto.ReservationResponse;
import com.quickbook.quickbookbackend.service.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
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
    
    @GetMapping("/room/{roomId}") // USER
    public List<ReservationResponse> getAllReservationsForHotelRoom(@PathVariable Integer roomId){
        return reservationService.getAllReservationsForHotelRoom(roomId);
    }

    @GetMapping("/guests") // USER
    public List<ReservationResponse> getAllReservationsForGuest(Principal principal){
        return reservationService.getAllReservationsForGuest(principal.getName());
    }
    
    @PostMapping("/room/{roomId}/date/{reservationDate}") // USER
    public ReservationResponse makeReservation(@PathVariable Integer roomId, 
                                               @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationDate, 
                                               Principal principal){
        return reservationService.makeReservation(roomId, reservationDate, principal);
    }
    @DeleteMapping("/guests/{reservationId}") // USER
    public ReservationResponse deleteReservation(@PathVariable Integer reservationId, Principal principal){
        return reservationService.deleteReservation(reservationId, principal);
    }
}
