package com.quickbook.quickbookbackend.service;

import com.quickbook.quickbookbackend.dto.HotelRequest;
import com.quickbook.quickbookbackend.dto.HotelResponse;
import com.quickbook.quickbookbackend.entity.Hotel;
import com.quickbook.quickbookbackend.repository.HotelRepository;
import org.springframework.stereotype.Service;

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
            throw new NoSuchElementException("Hotel not found with id: " + id);
        }
        
        return new HotelResponse(optionalHotel.get());
    }
    
    public HotelResponse updateHotel(HotelRequest hotelRequest){
        
        Integer id = hotelRequest.getId();
        if (id == null) {
            throw new NoSuchElementException("Hotel ID is required for update");
        }
        
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelRequest.getId());        
        if (optionalHotel.isEmpty()) {
            throw new NoSuchElementException("Hotel not found with id: " + id);
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

        if (!Objects.equals(hotelRequest.getNumberOfRooms(), savedHotel.getNumberOfRooms())) {
            savedHotel.setNumberOfRooms(hotelRequest.getNumberOfRooms());
        }
        
        hotelRepository.save(savedHotel);
        return new HotelResponse(savedHotel);
    }
    
}
