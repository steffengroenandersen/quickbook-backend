package com.quickbook.quickbookbackend.dto;

import com.quickbook.quickbookbackend.entity.Guest;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuestRequest {
    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    public static Guest getGuestEntity(GuestRequest guestRequest) {
        return new Guest(guestRequest.username,
                guestRequest.email,
                guestRequest.password,
                guestRequest.firstName,
                guestRequest.lastName);
    }
}
