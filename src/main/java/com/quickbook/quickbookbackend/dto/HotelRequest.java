package com.quickbook.quickbookbackend.dto;

import com.quickbook.quickbookbackend.entity.Hotel;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelRequest {
    private String name;
    private String street;
    private String city;
    private int zip;
    private String country;
    private int numberOfRooms;
    
    public static Hotel getHotelEntity(HotelRequest hotelRequest){
        return new Hotel(
                hotelRequest.getName(),
                hotelRequest.getStreet(),   
                hotelRequest.getCity(),
                hotelRequest.getZip(),
                hotelRequest.getCountry()
        );
    }
}
