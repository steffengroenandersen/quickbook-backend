package com.quickbook.quickbookbackend.repository;

import com.quickbook.quickbookbackend.entity.Reservation;
import com.quickbook.quickbookbackend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    
    List<Reservation> findByRoomId(int roomId);
    
    List<Reservation> findByGuestUsername(String guestUsername);
    
    List<Reservation> findByRoomAndReservationDate(Room room, LocalDate reservationDate);
}
