package com.quickbook.quickbookbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.quickbook.quickbookbackend.entity.Guest;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GuestResponse {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime edited;

    public GuestResponse(Guest guest) {
        this.username = guest.getUsername();
        this.email = guest.getEmail();
        this.firstName = guest.getFirstName();
        this.lastName = guest.getLastName();
        this.created = guest.getCreated();
        this.edited = guest.getEdited();
    }
}
