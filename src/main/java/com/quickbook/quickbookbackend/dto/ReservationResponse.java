package com.quickbook.quickbookbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.quickbook.quickbookbackend.entity.Reservation;
import com.quickbook.quickbookbackend.entity.Room;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {
    private int id;
    private GuestResponse guest;
    private Room room;
    private LocalDateTime reservationDate;

    public ReservationResponse(Reservation reservation) {
        this.id = reservation.getId();
        this.guest = new GuestResponse(reservation.getGuest());
        this.room = reservation.getRoom();
        this.reservationDate = reservation.getReservationDate();
    }
}
