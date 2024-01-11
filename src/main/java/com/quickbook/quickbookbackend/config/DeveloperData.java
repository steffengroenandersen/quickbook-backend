package com.quickbook.quickbookbackend.config;

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
        
        // Add Hotels
        List<Hotel> hotelList = new ArrayList<>();
        
        Hotel hotel1 = new Hotel("Hotel1");
        Hotel hotel2 = new Hotel("Hotel2");

        // Create and set rooms for Hotel 1
        List<Room> roomList = new ArrayList<>();
        Room room1 = new Room();
        Room room2 = new Room();

        roomList.add(room1);
        roomList.add(room2);
        hotel1.setRooms(roomList);

        // Create and set rooms for Hotel 2
        List<Room> roomList1 = new ArrayList<>();
        Room room3 = new Room();
        Room room4 = new Room();
        
        roomList1.add(room3);
        roomList1.add(room4);
        hotel2.setRooms(roomList1);
        
        // Add hotels to list
        hotelList.add(hotel1);
        hotelList.add(hotel2);

        // Save rooms and hotels
        roomRepository.saveAll(roomList);
        roomRepository.saveAll(roomList1);
        hotelRepository.saveAll(hotelList);
        
        // Add reservation
        Reservation reservation1 = new Reservation();
        reservation1.setGuest(guest1);
        reservation1.setRoom(room1);
        reservationRepository.save(reservation1);
        
        Reservation reservation2 = new Reservation();
        reservation2.setGuest(guest1);
        reservation2.setRoom(room3);
        reservationRepository.save(reservation2);
        
        

        
        
        
        
    }
}
