package com.quickbook.quickbookbackend.repository;

import com.quickbook.quickbookbackend.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
