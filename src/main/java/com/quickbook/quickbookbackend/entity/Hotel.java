package com.quickbook.quickbookbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime edited;

    private String name;
    private String street;
    private String city;
    private int zip;
    private String country;
    
    private int numberOfRooms;

    @OneToMany
    @JoinColumn(name = "hotel_id")
    List<Room> rooms;

    public Hotel(String name, String street, String city, int zip, String country, int numberOfRooms) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.country = country;
        this.numberOfRooms = numberOfRooms;
    }
}
