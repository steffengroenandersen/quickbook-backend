package com.quickbook.quickbookbackend.api;

import com.quickbook.quickbookbackend.dto.GuestRequest;
import com.quickbook.quickbookbackend.dto.GuestResponse;
import com.quickbook.quickbookbackend.dto.HotelRequest;
import com.quickbook.quickbookbackend.dto.HotelResponse;
import com.quickbook.quickbookbackend.service.HotelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    
    HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping // ANONYMOUS
    public List<HotelResponse> getAllHotels(){
        return hotelService.getAllHotels();
    }
    
    @GetMapping("/{id}") // ANONYMOUS
    public HotelResponse getHotel(@PathVariable int id){
        return hotelService.getHotel(id);
    }
    
    @PostMapping() // ADMIN
    public HotelResponse createHotel(@RequestBody HotelRequest hotelRequest){
        System.out.println("createHotel()");
        System.out.println(hotelRequest);
        
        return hotelService.createHotel(hotelRequest);
    }
    
    @PatchMapping()
    public HotelResponse updateHotel(@RequestBody HotelRequest hotelRequest){
        return hotelService.updateHotel(hotelRequest);
    }

}
