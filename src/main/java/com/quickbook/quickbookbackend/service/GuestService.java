package com.quickbook.quickbookbackend.service;

import com.quickbook.quickbookbackend.dto.GuestRequest;
import com.quickbook.quickbookbackend.dto.GuestResponse;
import com.quickbook.quickbookbackend.entity.Guest;
import com.quickbook.quickbookbackend.repository.GuestRepository;
import com.quickbook.security.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class GuestService {

    GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<GuestResponse> getAllGuests(){
        List<Guest> guests = guestRepository.findAll();
        List<GuestResponse> response = guests.stream().map((guest -> new GuestResponse(guest))).toList();
        return response;
    }
    
    public GuestResponse createGuest(GuestRequest guestRequest) {
        Guest guest = GuestRequest.getGuestEntity(guestRequest);

        guestRepository.findById(guest.getUsername()).ifPresent(u -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Guest already exists");
        });

        if(guestRepository.findByEmail(guest.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        guest.addRole(Role.USER);
        guestRepository.save(guest);
        return new GuestResponse(guest);
    }

    public Page<GuestResponse> getAllGuestsPageable(Pageable pageable){
        Page<Guest> guests = guestRepository.findAll(pageable);
        System.out.println("Guests: " + guests);

        List<GuestResponse> guestResponses = guests.stream().map(guest -> new GuestResponse(guest)).toList();
        System.out.println("GuestResponses: " + guestResponses);

        return new PageImpl<>(guestResponses, pageable, guests.getTotalElements());
    }
}
