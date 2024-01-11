package com.quickbook.quickbookbackend.service;

import com.quickbook.quickbookbackend.dto.HotelRequest;
import com.quickbook.quickbookbackend.dto.HotelResponse;
import com.quickbook.quickbookbackend.dto.RoomRequest;
import com.quickbook.quickbookbackend.entity.Hotel;
import com.quickbook.quickbookbackend.entity.Room;
import com.quickbook.quickbookbackend.repository.HotelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class HotelService {
    
    HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public HotelResponse createHotel(HotelRequest hotelRequest) {
        Hotel newHotel = HotelRequest.getHotelEntity(hotelRequest);
        hotelRepository.save(newHotel);
        return new HotelResponse(newHotel);
    }
    
    public List<HotelResponse> getAllHotels(){
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream().map((hotel -> new HotelResponse(hotel))).toList();
    }
    
    public HotelResponse getHotel(int id){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        
        if(!optionalHotel.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found with id: " + id);
        }
        
        return new HotelResponse(optionalHotel.get());
    }
    
    public HotelResponse updateHotel(Integer id, HotelRequest hotelRequest){
        
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);        
        if (optionalHotel.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found with id: " + id);

        }
        
        Hotel savedHotel = optionalHotel.get();

        if (!Objects.equals(hotelRequest.getName(), savedHotel.getName())) {
            savedHotel.setName(hotelRequest.getName());
        }

        if (!Objects.equals(hotelRequest.getStreet(), savedHotel.getStreet())) {
            savedHotel.setStreet(hotelRequest.getStreet());
        }

        if (!Objects.equals(hotelRequest.getCity(), savedHotel.getCity())) {
            savedHotel.setCity(hotelRequest.getCity());
        }

        if (!Objects.equals(hotelRequest.getZip(), savedHotel.getZip())) {
            savedHotel.setZip(hotelRequest.getZip());
        }

        if (!Objects.equals(hotelRequest.getCity(), savedHotel.getCountry())) {
            savedHotel.setCountry(hotelRequest.getCountry());
        }
        
        hotelRepository.save(savedHotel);
        return new HotelResponse(savedHotel);
    }
    
    public HotelResponse deleteHotel(Integer id){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found with id: " + id);
        }
        
        Hotel savedHotel = optionalHotel.get();
        
        // TODO: Create ReservationService method that checks of a hotels associated rooms have reservations and return boolean
        boolean checkHotel = false;
        if (checkHotel) {
            //throw new IllegalStateException("Cannot delete hotel with rooms having reservations");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cannot delete hotel with rooms having reservations");
        }
        
        hotelRepository.delete(savedHotel);
        return new HotelResponse(savedHotel);
    }
    
    public HotelResponse createRoom(Integer id, RoomRequest roomRequest){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found with id: " + id);
        }

        Hotel savedHotel = optionalHotel.get();
        
        savedHotel.addRoom(roomRequest);
        
        hotelRepository.save(savedHotel);
        
        return new HotelResponse(savedHotel);
    }    
    

    
}
