package com.quickbook.quickbookbackend.api;

import com.quickbook.quickbookbackend.dto.GuestRequest;
import com.quickbook.quickbookbackend.dto.GuestResponse;
import com.quickbook.quickbookbackend.dto.HotelRequest;
import com.quickbook.quickbookbackend.dto.HotelResponse;
import com.quickbook.quickbookbackend.service.HotelService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    
    HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping()
    public HotelResponse createHotel(@RequestBody HotelRequest hotelRequest){
        System.out.println("createHotel()");
        System.out.println(hotelRequest);
        
        return hotelService.createHotel(hotelRequest);
    }
}
