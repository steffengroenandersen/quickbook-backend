package com.quickbook.quickbookbackend.api;

import com.quickbook.quickbookbackend.dto.*;
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
    
    @PatchMapping("/{id}")
    public HotelResponse updateHotel(@PathVariable Integer id, @RequestBody HotelRequest hotelRequest){
        return hotelService.updateHotel(id, hotelRequest);
    }
    
    @DeleteMapping("/{id}")
    public HotelResponse deleteHotel(@PathVariable Integer id){
        return hotelService.deleteHotel(id);
    }
    
    @PostMapping("/{id}/rooms")
    public HotelResponse createRoom(@PathVariable Integer id, @RequestBody RoomRequest roomRequest){
        return hotelService.createRoom(id, roomRequest);
    }

    
}
