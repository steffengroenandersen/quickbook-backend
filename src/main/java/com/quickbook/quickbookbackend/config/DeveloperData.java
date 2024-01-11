package com.quickbook.quickbookbackend.config;

import com.quickbook.quickbookbackend.dto.RoomRequest;
import com.quickbook.quickbookbackend.entity.Guest;
import com.quickbook.quickbookbackend.entity.Hotel;
import com.quickbook.quickbookbackend.entity.Reservation;
import com.quickbook.quickbookbackend.entity.Room;
import com.quickbook.quickbookbackend.repository.GuestRepository;
import com.quickbook.quickbookbackend.repository.HotelRepository;
import com.quickbook.quickbookbackend.repository.ReservationRepository;
import com.quickbook.quickbookbackend.repository.RoomRepository;
import com.quickbook.security.entity.Role;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DeveloperData implements ApplicationRunner {

    GuestRepository guestRepository;
    HotelRepository hotelRepository;
    RoomRepository roomRepository;
    ReservationRepository reservationRepository;

    public DeveloperData(GuestRepository guestRepository, HotelRepository hotelRepository, RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.guestRepository = guestRepository;
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("RUNNING PROGRAM......................");
        System.out.println("INSERTING DEVELOPER DATA......................");

        // Add Admins
        Guest admin1 = new Guest("admin1", "steffen@localhost.com", "pass", "Steffen", "Andersen");
        admin1.addRole(Role.ADMIN);
        guestRepository.save(admin1);

        // Add Guests
        Guest guest1 = new Guest("user1", "user1@localhost.com", "pass", "Peter", "Andersen");
        guest1.addRole(Role.USER);
        guestRepository.save(guest1);
        
        // Generate and save hotels and rooms
        List<Hotel> hotels = generateHotels();
        
        // Generate and save reservations
        List<Reservation> reservations = generateReservations(guest1, hotels);
        
        
                
    }
    
    public List<Hotel> generateHotels(){
        List<Hotel> hotels = new ArrayList<>();
        
        // Generate hotels
        for(int i = 1; i <= 20; i++){
            String name = "Hotel_" + i;
            String street = "Street_" + i;
            String city = "City_" + i;
            int zip = 1000;
            String country = "Denmark";
            
            Hotel newHotel = new Hotel(name, street, city, zip, country);
            hotels.add(newHotel);
        }

        // Generate rooms for hotels and set them
        for(Hotel hotel : hotels){
            
            RoomRequest roomRequest1 = new RoomRequest(2);
            RoomRequest roomRequest2 = new RoomRequest(2);
            RoomRequest roomRequest3 = new RoomRequest(2);
            hotel.addRoom(roomRequest1);
            hotel.addRoom(roomRequest2);
            hotel.addRoom(roomRequest3);
            
            /*
            List<Room> roomsToAdd = new ArrayList<>();
            
            Room room1 = new Room(1, 2);
            Room room2 = new Room(1, 2);
            Room room3 = new Room(1, 2);
            
            roomsToAdd.add(room1);
            roomsToAdd.add(room2);
            roomsToAdd.add(room3);
            
            hotel.setRooms(roomsToAdd);
            
            // Save rooms
            //roomRepository.saveAll(roomsToAdd);
             */

        }
        
        // Save hotels
        hotelRepository.saveAll(hotels);
        return hotels;
    }
    
    
    public List<Reservation> generateReservations(Guest guest, List<Hotel> hotels){
        List<Reservation> reservations = new ArrayList<>();
        LocalDateTime reservationDate = LocalDateTime.now();
        
        Reservation reservation1 = new Reservation(guest, hotels.get(0).getRooms().get(0), reservationDate);
        Reservation reservation2 = new Reservation(guest, hotels.get(0).getRooms().get(0), reservationDate);
        Reservation reservation3 = new Reservation(guest, hotels.get(1).getRooms().get(2), reservationDate);
        
        reservations.add(reservation1);
        reservations.add(reservation2);
        reservations.add(reservation3);
        
        reservationRepository.saveAll(reservations);
        
        return reservations;
    }
    

    

    

}
