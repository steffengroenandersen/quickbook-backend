package com.quickbook.quickbookbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.quickbook.quickbookbackend.entity.Hotel;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelResponse {
    private int id;
    private String name;
    private String street;
    private String city;
    private int zip;
    private String country;
    private int numberOfRooms;

    public HotelResponse(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.street = hotel.getStreet();
        this.city = hotel.getCity();
        this.zip = hotel.getZip();
        this.country = hotel.getCountry();
        this.numberOfRooms = hotel.getNumberOfRooms();
    }
}
