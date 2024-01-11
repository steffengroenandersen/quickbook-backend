package com.quickbook.quickbookbackend.repository;

import com.quickbook.quickbookbackend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
