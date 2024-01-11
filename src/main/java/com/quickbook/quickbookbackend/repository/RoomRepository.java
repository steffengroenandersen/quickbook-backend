package com.quickbook.quickbookbackend.repository;

import com.quickbook.quickbookbackend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
