package com.quickbook.quickbookbackend.service;

import com.quickbook.quickbookbackend.dto.HotelRequest;
import com.quickbook.quickbookbackend.dto.HotelResponse;
import com.quickbook.quickbookbackend.entity.Hotel;
import com.quickbook.quickbookbackend.repository.HotelRepository;
import com.quickbook.quickbookbackend.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    HotelService hotelService;
    @Mock
    HotelRepository hotelRepository;
    @Mock
    ReservationRepository reservationRepository;

    @BeforeEach
    void setUp(){
        hotelService = new HotelService(hotelRepository, reservationRepository);
    }
    
    Hotel mockHotel(){
        Hotel mockHotel = new Hotel("mockName","mockStreet","mockCity",9999,"mockCountry");
        return mockHotel;
    }
    
    @Test
    void createHotel() {
        // Arrange
        Hotel mockHotel = mockHotel();
        HotelRequest hotelRequest = new HotelRequest(mockHotel.getName(),
                mockHotel.getStreet(),
                mockHotel.getCity(), 9999,
                mockHotel.getCountry(), 1);
        when(hotelRepository.save(any(Hotel.class))).thenReturn(mockHotel);

        // Act
        HotelResponse hotelResponse = hotelService.createHotel(hotelRequest);

        // Assert
        verify(hotelRepository, times(1)).save(any(Hotel.class));
    }
}
