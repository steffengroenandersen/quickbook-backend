package com.quickbook.quickbookbackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime edited;
    
    @ManyToOne
    @JoinColumn(name = "guest_id")
    Guest guest;
    
    @ManyToOne
    @JoinColumn(name = "room_id")
    Room room;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate reservationDate;

    public Reservation(Guest guest, Room room, LocalDate reservationDate) {
        this.guest = guest;
        this.room = room;
        this.reservationDate = reservationDate;
    }
}
