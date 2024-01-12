package com.quickbook.quickbookbackend.repository;
import com.quickbook.quickbookbackend.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, String> {
    
    Guest findByEmail(String email);
    
    Guest findByUsername(String username);
}
