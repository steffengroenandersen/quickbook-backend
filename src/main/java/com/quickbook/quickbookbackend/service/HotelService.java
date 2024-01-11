package com.quickbook.quickbookbackend.service;

import com.quickbook.quickbookbackend.dto.GuestRequest;
import com.quickbook.quickbookbackend.dto.GuestResponse;
import com.quickbook.quickbookbackend.dto.HotelRequest;
import com.quickbook.quickbookbackend.dto.HotelResponse;
import com.quickbook.quickbookbackend.entity.Guest;
import com.quickbook.quickbookbackend.entity.Hotel;
import com.quickbook.quickbookbackend.repository.HotelRepository;
import com.quickbook.security.entity.Role;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
}
