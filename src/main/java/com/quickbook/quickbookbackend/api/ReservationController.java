package com.quickbook.quickbookbackend.api;

import com.quickbook.quickbookbackend.dto.HotelResponse;
import com.quickbook.quickbookbackend.dto.ReservationResponse;
import com.quickbook.quickbookbackend.service.ReservationService;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    
    @GetMapping("/room/{roomId}") // ANONYMOUS
    public List<ReservationResponse> getAllReservationsForHotelRoom(@PathVariable Integer roomId){
        return reservationService.getAllReservationsForHotelRoom(roomId);
    }

    @GetMapping("/guest") // USER
    public List<ReservationResponse> getAllReservationsForGuest(Principal principal){
        return reservationService.getAllReservationsForGuest(principal.getName());
    }

    @DeleteMapping("/guest/{reservationId}") // USER
    public ReservationResponse deleteReservation(@PathVariable Integer reservationId, Principal principal){
        return reservationService.deleteReservation(reservationId, principal);
    }
    
    @PostMapping("/room/{roomId}/date/{reservationDate}") // USER
    public ReservationResponse makeReservation(@PathVariable Integer roomId, 
                                               @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationDate, Principal principal){
        return reservationService.makeReservation(roomId, reservationDate, principal);
    }

}
