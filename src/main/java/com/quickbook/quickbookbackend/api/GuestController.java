package com.quickbook.quickbookbackend.api;

import com.quickbook.quickbookbackend.dto.GuestRequest;
import com.quickbook.quickbookbackend.dto.GuestResponse;
import com.quickbook.quickbookbackend.service.GuestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
    GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    // ANONYMOUS ENDPOINTS
    @PostMapping()
    public GuestResponse createGuest(@RequestBody GuestRequest guestRequest){
        System.out.println("createGuest");
        System.out.println(guestRequest);
        return guestService.createGuest(guestRequest);
    }

    // ADMIN ENDPOINTS
    @GetMapping
    public List<GuestResponse> getAllGuests(){
        return guestService.getAllGuests();
    }

    @GetMapping("/pageable")
    public Page<GuestResponse> getAllGuestsPageable(Pageable pageable) {
        System.out.println("getAllGuestsPageable()");
        return guestService.getAllGuestsPageable(pageable);
    }
}
