package com.quickbook.quickbookbackend.entity;

import com.quickbook.quickbookbackend.dto.RoomRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "hotel_id")
    List<Room> rooms = new ArrayList<>();
    
    public Hotel(String name, String street, String city, int zip, String country) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.country = country;
    }
    
    public void addRoom(RoomRequest roomRequest){
        int roomNumber = rooms.size()+1;
        int numberOfBeds = roomRequest.getNumberOfBeds();
        
        rooms.add(new Room(roomNumber,numberOfBeds));
    }
}
