package com.quickbook.quickbookbackend.service;

import com.quickbook.quickbookbackend.dto.HotelResponse;
import com.quickbook.quickbookbackend.entity.Hotel;
import com.quickbook.quickbookbackend.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    HotelService hotelService;
    
    @Mock
    HotelRepository hotelRepository;
    
    @BeforeEach
    void setUp() {
        // Arrange
        hotelService = new HotelService(hotelRepository);
        
        List<Hotel> mockHotels = new ArrayList<>(Arrays.asList(
                new Hotel("Hotel A", "123 Main St", "City A", 12345, "Country A"),
                new Hotel("Hotel B", "456 Oak St", "City B", 67890, "Country B")
        ));

        
        when(hotelRepository.findAll()).thenReturn(mockHotels);
        
        when(hotelRepository.findById(1)).thenReturn(Optional.of(mockHotels.get(1)));
    }

    @Test
    void createHotel() {
    }

    @Test
    void getAllHotels_ReturnsAllHotelsAsHotelResponses() {
        // Act
        List<HotelResponse> hotelResponses = hotelService.getAllHotels();
        
        // Assert
        assertEquals(2, hotelResponses.size(),"The getAllHotels method should find all hotels and return as HotelResponses");

        for (HotelResponse hotelResponse : hotelResponses) {
            assertTrue(hotelResponse instanceof HotelResponse, "Each element should be an instance of HotelResponse");
        }
    }

    @Test
    void getHotel_ReturnsHotelResponseById() {
        // Act
        HotelResponse hotelResponse1 = hotelService.getHotel(1);
        
        // Assert
        assertNotNull(hotelResponse1);
        assertEquals("Hotel B", hotelResponse1.getName());


    }

    @Test
    void updateHotel() {
    }

    @Test
    void deleteHotel() {
    }

    @Test
    void createRoom() {
    }
}