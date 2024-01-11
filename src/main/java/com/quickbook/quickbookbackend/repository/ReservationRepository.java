package com.quickbook.quickbookbackend.repository;

import com.quickbook.quickbookbackend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByRoomId(int roomId);

    List<Reservation> findByGuestUsername(String guestUsername);
}
